package com.zhangjie.springstudy;

import com.zhangjie.annotation.bean.Person;
import com.zhangjie.annotation.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationIOCTest {

    ApplicationContext applicationContext = new  AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public void test01(){

        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }


    }

}
