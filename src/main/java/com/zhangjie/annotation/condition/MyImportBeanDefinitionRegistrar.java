package com.zhangjie.annotation.condition;

import com.zhangjie.annotation.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata AnnotationMetadata：当前类的注解信息
     * @param registry BeanDefinitionRegistry：BeanDefinition注册类
     *                 把所有许哟啊添加到容器中的bean，
     *                 通过调用 BeanDefinitionRegistry的registerBeanDefinition方法手工进行组件注册
     *
     */

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("com.zhangjie.annotation.bean.Red");
        boolean definition2 = registry.containsBeanDefinition("com.zhangjie.annotation.bean.Blue");

        if (definition && definition2){
            // 可以指定bean的名称和Bean的定义信息
            registry.registerBeanDefinition("rainBow", new RootBeanDefinition(RainBow.class));
        }
    }
}
