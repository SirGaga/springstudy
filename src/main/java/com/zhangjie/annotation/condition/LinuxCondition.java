package com.zhangjie.annotation.condition;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
    /**
     *
     * @param context 判断条件能使用的上下文
     * @param metadata 当前标注了@Conditional注解的注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 是否linux系统
        // 1.能获取到ioc使用的BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 3.获取当前环境信息
        Environment environment = context.getEnvironment();
        // 4.获取Bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");
        if (property.contains("linux")){
            return true;
        }
        return false;
    }
}
