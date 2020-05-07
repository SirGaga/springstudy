package com.zhangjie.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个Spring定义的工厂bean
 */
public class ColorFactoryBean implements FactoryBean {
    //返回一个color对象，这个对象将会添加到容器中

    public Object getObject() throws Exception {
        return new Color();
    }

    //注册到容器中的bean的类类型

    public Class<?> getObjectType() {
        return Color.class;
    }

    // 注册到容器中对象是否为单实例
    // 多实例每次获取都会创建一个新的实例，即调getObject方法

    public boolean isSingleton() {
        return true;
    }
}
