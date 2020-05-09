package com.zhangjie.annotation.ext;

import com.zhangjie.annotation.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理：
 * BeanPostProcessor:bean 后置处理器，在bean创建对象初始化前后进行拦截工作的
 * 1、BeanFactoryPostProcessor : BeanFactory 的后置处理器，在beanFactory标准初始化之后调用，即所有的bean定义已经加载到beanFactory中，但是还没有创建bean实例
 *
 * 1)IoC 容器创建对象
 * 2)invokeBeanFactoryPostProcessors(beanFactory) 执行BeanFactoryPostProcessors
 *      如何找到所有的 BeanFactoryPostProcessor 并执行他们的方法：
 *          1)直接在BeanFactory中找到所有类型是 BeanFactoryPostProcessor 的组件，并执行他们的方法
 *          2)在初始化创建其他组件之前执行
 *
 * 2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *      在所有bean定义信息将要被加载但是bean实例还未被创建的时候执行
 *
 *      优先于 BeanFactoryPostProcessor 执行；
 *      利用 BeanDefinitionRegistryPostProcessor 给容器中再添加一些组件
 *    原理：
 *        1.创建容器
 *        2.调用refresh()方法 -> invokeBeanFactoryPostProcessors(beanFactory)
 *        3.先从容器中获取到所有的 BeanDefinitionRegistryPostProcessor 组件，
 *          依次触发所有的 postProcessBeanDefinitionRegistry()
 *          再触发 postProcessBeanFactory() 方法 BeanFactoryPostProcessor
 *        4.再从容器中找到 BeanFactoryPostProcessor 组件，依次触发 postProcessBeanFactory() 方法
 * 3、ApplicationListener:监听容器中发布的事件，完成事件驱动模型的开发
 *      ApplicationListener<E extends ApplicationEvent> extends EventListener
 *      监听 ApplicationEvent 及其下面的子事件
 *    步骤：
 *        1.写一个监听器（ApplicationEvent的实现类）来监听某个事件（ApplicationEvent 及其 子类）
 *          或者使用 @EventListener 注解
 *              原理：使用 EventListenerMethodProcessor 处理器来解析方法上的 @EventListener 注解
 *        2.把监听器加入到容器中
 *        3.只要容器中有相关事件的发布，就能监听到这个事件
 *          ContextRefreshedEvent：容器刷新完成（所有bean都完全创建）会发布这个事件
 *        4.发布一个事件
 *    原理：
 *        ContextRefreshedEvent、AnnotationExt$1[source=我发布的事件]、ContextClosedEvent
 *        1.ContextRefreshedEvent 事件：
 *            1.容器创建对象：refresh();
 *            2.finishRefresh() 容器刷新完成，容器刷新完成会发布ContextRefreshEvent 事件
 *            3.publishEvent(new ContextRefreshContext(this))
 *              发布流程：获取到事件的多播器（派发器）：getApplicationEventMulticaster()
 *              multicastEvent 派发事件
 *              获取到所有的监听器 ApplicationListener
 *                  for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                  如果有 Executor 可以支持使用 Executor 进行异步派发
 *                      Executor executor = getTaskExecutor();
 *                  否则 同步的方式 直接执行 listener 方法
 *                      invokeListener(listener, event); 回调 onApplicationEvent(ApplicationEvent event) 方法
 *        2.自己发布事件（直接走1.3流程）
 *        3.容器关闭会发布 ContextClosedEvent 事件
 *    事件多播器：
 *        1.容器创建对象：调用 refresh() 方法
 *        2.initApplicationEventMulticaster() 初始化 ApplicationEventMulticaster
 *          先去容器中找有没有id=“applicationEventMulticaster”的组件，
 *          如果没有就创建一个SimpleApplicationEventMulticaster，并且加入到容器中，就可以在其他组件要派发事件的时候自动注入 applicationEventMulticaster 组件
 *    容器中有哪些监听器：
 *        1.事件多播器注册完成以后调用 registerListeners(); 方法获取到所用的 ApplicationListener 组件，并注册到多播器中 （getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);）
 *
 *    SmartInitializingSingleton 原理：
 *        1.ioc容器创建对象，refresh()刷新容器
 *        2.finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean
 *          1.先创建所有的单实例bean，调用 getBean()方法来创建
 *          2.获取所有创建好的单实例bean，判断是否是实现了 SmartInitializingSingleton 接口的bean
 *              如果是 调用 afterSingletonsInstantiated() 方法
 *
 *
 */
@ComponentScan(value = {"com.zhangjie.annotation.ext"})
@Configuration
public class ExtConfig {
    @Bean
    public Blue blue(){
        return new Blue();
    }
}
