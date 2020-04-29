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
 *      8)
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
