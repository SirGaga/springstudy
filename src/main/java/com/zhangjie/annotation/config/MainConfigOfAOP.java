package com.zhangjie.annotation.config;

import com.zhangjie.annotation.aop.LogAspect;
import com.zhangjie.annotation.condition.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:动态代理，能在程序运行期间动态的将某段代码切入到指定方法指定位置运行的编程方式
 * 1.导入AOP模块：Spring AOP spring-aspects
 * 2.定义一个业务逻辑类 MathCalculator 在业务逻辑运行的时候将日志进行打印
 * 3.定义一个日志切面类 LogAspect ：里边的方法需要动态感知除法(div)运行到哪里
 *      通知方法：
 *          前置通知(@Before)（logStart，目标方法运行前运行），
 *          后置通知(@After)（logEnd，目标方法运行结束之后通知），
 *          返回通知(@AfterReturning)（logReturn，目标方法正常返回之后运行），
 *          异常通知(@AfterThrowing)（logException，目标方法运行出现异常以后运行），
 *          环绕通知(@Around)（动态代理，手动推动目标方法运行（joinPoint.proceed()））
 * 4.给切面类的目标方法标注何时何地运行（通知注解）
 * 5.将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6.告诉Spring那个类是切面类（@Aspect）
 * 7.在配置类上添加注解：@EnableAspectJAutoProxy，启动AspectJ的自动代理
 *
 * 在Spring中有很多的@EnableXXX注解，他们的作用就是开启某一项功能的支持
 * 开启AOP的核心三个步骤：
 * 1.将业务逻辑组件和切面类都加入到容器中，并告诉Spring哪个是切面类
 * 2.在切面类上的每个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 * 3.开启基于注解的aop模式 @EnableAspectJAutoProxy
 * AOP原理【给容器中注册了什么组件，这个组件什么时候工作，和它工作时候的功能是什么】：
 *     1.@EnableAspectJAutoProxy：
 *          @Import(AspectJAutoProxyRegistrar.class)给容器中导入 AspectJAutoProxyRegistrar ，
 *          利用 AspectJAutoProxyRegistrar 自定义给容器中注册bean，
 *          internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *          给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *
 *     2.AnnotationAwareAspectJAutoProxyCreator：根据这个类的集合和接口实现的父子关继承树系发现：
 *     最需要关注的还是SmartInstantiationAwareBeanPostProcessor（后置处理器，bean初始化完成前后做一些事情）和BeanFactoryAware（自动装配BeanFactory）
 *     org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#setBeanFactory(org.springframework.beans.factory.BeanFactory)
 *     org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessBeforeInstantiation(java.lang.Class, java.lang.String)
 *     org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization(java.lang.Object, java.lang.String)
 *
 *
 *     org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator#setBeanFactory(org.springframework.beans.factory.BeanFactory)
 *     org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator#initBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 *
 *     org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator#initBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 * 流程：
 *  1.传入配置类，创建ioc容器
 *  2.注册配置类，调用refresh()方法，刷新容器
 *  3.register(annotatedClasses);注册bean的后置处理器来方便拦截bean的创建
 *      1）先获取IoC容器中已经定义了的需要创建的对象的所有BeanPostProcessor
 *      2）给容器中加别的BeanPostProcessor
 *      3）优先注册实现了PriorityOrdered接口的 BeanPostProcessor，
 *      4）再给容器中注册实现了Ordered接口的 BeanPostProcessor
 *      5）最后注册没有实现优先级接口的 BeanPostProcessor
 *      6）注册 BeanPostProcessor ，实际上就是创建 BeanPostProcessor 对象，保存在容器中
 *          创建 internalAutoProxyCreator 的 BeanPostProcessor 【AnnotationAwareAspectJAutoProxyCreator】过程
 *          1） 创建 Bean 的实例
 *          2） 装配 Bean 的属性，属性赋值
 *          3） initializeBean，初始化bean：
 *              1） invokeAwareMethods()：处理 Aware 接口的方法回调
 *              2） applyBeanPostProcessorBeforeInitialization()：应用后置处理器的的postProcessorBeforeInitialization()
 *              3） invokeInitMethods()：执行自定义的初始化方法
 *              4） applyBeanPostProcessorAfterInitialization():应用后置处理器的的postProcessorAfterInitialization()
 *          4) BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功-->aspectJAdvisorBuilder
 *      7) 把 BeanPostProcessor 注册到 BeanFactory 中：beanFactory.addBeanPostProcessor(postProcessor)
 *      ============以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程============
 *      AnnotationAwareAspectJAutoProxyCreator -> InstantiationAwareBeanPostProcessor
 *  4.finishBeanFactoryInitialization(beanFactory) 完成BeanFactory初始化工作，创建剩下的单实例bean
 *      1) 遍历获取容器中所有的bean，依次创建 getBean(beanName);
 *      getBean(beanName) -> getSingleton() ->
 *      2) 创建bean
 *      【AnnotationAwareAspectJAutoProxyCreator 在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor 会调用postProcessBeforeInitialization()】
 *          1)先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建
 *          只要被创建好的bean都会被缓存起来，保证bean只创建一次
 *          2)createBean(beanName)创建bean，AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前先尝试返回bean的实例
 *          【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *          【InstantiationAwareBeanPostProcessor 是在创建bean实例之前先尝试用后置处理器返回对象】
 *              1)resolveBeforeInstantiation(beanName,mbdToUse) 解析BeforeInstantiation
 *                希望后置处理器在此能返回一个代理对象,如果能返回代理对象就用
 *                1)后置处理器先尝试返回对象
 *                拿到所有后置处理器，如果是 InstantiationAwareBeanPostProcessor
 *                就执行后置处理器的 postProcessBeforeInstantiation
 *                bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *
 * 					if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                  }
 *              2)如果不能就调用 doCreateBean(beanName,mbdToUse,args);真正的去创建一个bean实例和3.6整个过程一样
 *              3)
 *
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 *  1) 每一个bean创建之前调用 postProcessBeforeInstantiation()
 *      关心 MathCalculator 和 LogAspect 的创建
 *      1) 判断当前bean是否在 advisedBeans 中（保存了所有需要增强的bean）
 *      2) 判断当前 bean 是否为基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean 或者是否是切面（是否有@Aspect注解）
 *      3）判断是否需要跳过
 *          1)获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
 *            每一个封装的通知方法的增强器是 InstantiationModelAwarePointcutAdvisor
 *            判断每一个增强器是否是 AspectJPointcutAdvisor 类型的,如果是返回true
 *          2)永远返回false
 *  2)创建对象
 *  postProcessAfterInitialization:
 *      return wrapIfNecessary(bean, beanName, cacheKey) 在需要的情况下包装，是否需要被切入，
 *      1)获取当前bean的所有增强器（通知方法）   Object[] specificInterceptors
 *          1)找到候选的能在当前bean使用的增强器（找哪些通知方法是需要切入当前bean方法的）
 *          2)获取到能在当前bean使用的增强器
 *          3)给增强器排序
 *      2)保存当前bean在advisedBeans中
 *      3)如果当前bean需要增强，创建当前bean的代理对象
 *          1)获取所有增强器（通知方法）
 *          2)保存到ProxyFactory中
 *          3)创建代理对象:Spring自动决定
 *              JdkDynamicAopProxy(config)//jdk动态代理
 *              ObjenesisCglibAopProxy(config)//cglib的动态代理
 *      4)给容器中返回当前组件使用cglib增强了的代理对象
 *      5)以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 * 3)目标方法执行：
 *      容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如，增强器，目标对象等等）
 *      1)CglibAopProxy.intercept();拦截目标方法的执行
 *      2)根据 ProxyFactory 对象获取将要执行的目标方法拦截器链
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1)List<Object> interceptorList保存所有拦截器5个
 *          一个默认的 ExposeInvocationInterceptor 和 4个增强器（通知方法）
 *          2)遍历所有的增强器，将其转为Interceptor，
 *          registry.getInterceptors(advisor)
 *          3)将增强器转为List<MethodInterceptor>
 *            如果是 MethodInterceptor，直接加入到集合中，
 *            如果不是，通过使用AdvisorAdapter将增强器转为 MethodInterceptor
 *            转换完后返回 MethodInterceptor 数组
 *      3)如果没有拦截器链，直接调用指定目标方法；
 *      4)如果有拦截器链（每一个通知方法又被包装为方法拦截器，利用 MethodInterceptor 机制执行），
 *      把需要执行的目标对象、目标方法、拦截器链等信息传入创建一个 CglibMethodInvocation，并调用他的proceed()方法
 *      5)拦截器链的触发过程
 *          1)如果没有拦截器直接执行目标方法，或者是拦截器索引和拦截器数组-1大小一样（也就是执行到了最后一个拦截器）->执行目标方法
 *          2)通过拦截器链（递归？主要还是设计模式）调用从默认的 ExposeInvocationInterceptor 调用 @AfterThrowing 标注的方法
 *          调用 @AfterReturning 标注的方法 调用 @After 标注的方法 调用 @Before 标注的方法，然后 执行 @Before 标注的方法，
 *          然后调用目标方法 然后调用 @After 方法
 *          然后 如果 @After 没有出错，调用  @AfterReturning 标注的方法 结束执行
 *              如果 @After 出错，@AfterReturning 直接抛出异常，并不捕获异常，抛出的异常交给 @AfterThrowing 来捕获，调用
 *              @AfterThrowing 标注的方法 结束执行
 *          链式获取每一个拦截器，拦截器执行 invoke 方法每一个拦截器等待下一个拦截器执行完成，返回以后再来执行
 *          通过拦截器链的机制，保证通知方法与目标方法的执行顺序
 * 总结：
 *  1)利用 @EnableAspectJAutoProxy 注解开启AOP的功能
 *  2)这个注解会在IoC容器中注册一个 AnnotationAwareAspectJAutoProxyCreator
 *  3)AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器
 *  4)利用容器的创建流程：
 *      1)registerBeanPostProcessors()，注册后置处理器，为所有后置处理器创建对象，创建 AnnotationAwareAspectJAutoProxyCreator 对象
 *      2)finishBeanFactoryInitialization，初始化剩下的单实例bean
 *          1)创建业务逻辑组件和切面
 *          2)AnnotationAwareAspectJAutoProxyCreator 拦截组件的创建过程
 *          3)在组建创建完成之后，判断组件是否需要包装（增强），
 *              是：切面的通知方法，包装成增强器（Advisor），给业务逻辑创建一个代理对象（cglib（类）、JdkDynamicAopProxy（接口））
 *          4)执行目标方法，
 *              1)代理对象执行目标方法
 *              2)CglibAopProxy.intercept()进行拦截
 *                  1)得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *                  2)利用拦截器的链式调用机制，依次进入每一个拦截器进行执行
 *                  3)效果：
 *                      前置通知
 *                      目标方法
 *                      后置通知
 *                      返回通知（无异常）/异常通知（有异常）
 *
 *
 *
 *
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {
    //业务逻辑类加入到容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }
    //切面类加入到容器中
    @Bean
    public LogAspect aspect(){
        return new LogAspect();
    }


}
