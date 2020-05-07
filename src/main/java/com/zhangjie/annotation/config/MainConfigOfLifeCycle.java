package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.zhangjie.annotation.bean")
/**
 * bean的声明周期：
 *      bean创建---初始化---销毁的过程
 * 容器管理bean的声明周期
 * 我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取bean对象的时候进行创建
 * 初始化：
 *      对象创建完成，并赋值好，调用初始化方法
 * 销毁：
 *      单实例：容器关闭的时候进行销毁
 *      多实例：容器不会管理这个bean，容器不会调用销毁方法
 * 总共有四种方法来自定义初始化和销毁方法
 * 1.指定初始化和销毁方法（xml文件中配置指定init-method=""  destroy-method=""）
 *      通过@Bean注解中initMethod = "init",destroyMethod = "destroy"
 * 2.通过让bean实现InitializingBean(定义初始化逻辑)和DisposableBean(定义销毁逻辑)
 * 3.使用JSR-250：
 *      @PostConstruct：在bean创建完成并且属性赋值完成，执行初始化
 *      @PreDestroy:在容器销毁bean之前通知进行清理工作
 * 4.BeanPostProcessor:bean的后置处理器接口，在bean初始化前后进行一些处理工作
 *      postProcessBeforeInitialization：在初始化之前工作
 *      postProcessAfterInitialization：在初始化之后工作
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 *      对象创建完成，并赋值好，调用初始化方法
 * BeanPostProcessor.postProcessAfterInitialization
 *
 * 遍历得到容器中所有的BeanPostProcessor，挨个执行beforeInitialization，
 * 一旦返回null，跳出for循环，不会执行后边的BeanPostProcessor.postProcessorsBeforeInitialization
 * BeanPostProcessor原理你
 * populateBean(beanName, mbd, instanceWrapper);给Bean进行属性赋值
 * {
 *  applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *  invokeInitMethods(beanName, wrappedBean, mbd);调用bean的初始化方法，执行初始化
 *  applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 * Spring底层对BeanPostProcessor的使用：
 *      bean赋值，注入其他组件，@AutoWired，生命周期注解功能
 */
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

}
