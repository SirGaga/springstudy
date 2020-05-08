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
 *        1.写一个监听器来监听某个事件（ApplicationEvent 及其 子类）
 *        2.把监听器加入到容器中
 *        3.只要容器中有相关事件的发布，就能监听到这个事件
 *          ContextRefreshedEvent：容器刷新完成（所有bean都完全创建）会发布这个事件
 *        4.发布一个事件
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
