package com.zhangjie.annotation.ext;

import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理：
 * BeanPostProcessor : bean 后置处理器，在bean创建对象初始化前后进行拦截工作的
 * BeanFactoryPostProcessor : BeanFactory 的后置处理器，在beanFactory标准初始化之后调用，即所有的bean定义已经加载，但是还没有创建bean实例
 */
@Configuration
public class ExtConfig {
}
