package com.zhangjie.springstudy;

import com.zhangjie.annotation.bean.Blue;
import com.zhangjie.annotation.bean.Person;
import com.zhangjie.annotation.config.MainConfig;
import com.zhangjie.annotation.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class AnnotationIOCTest {

    ApplicationContext applicationContext = new  AnnotationConfigApplicationContext(MainConfig.class);

    ApplicationContext applicationContext2 = new  AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test01(){

        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }


    }
    @Test
    public void test02(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test03(){
        String[] beanDefinitionNames = applicationContext2.getBeanDefinitionNames();
        System.out.println("容器创建完成...");
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
        // 默认是单实例的，无论多少次获取，都是我们在MainConfig2中new出来的对象
        Person person = (Person) applicationContext2.getBean("person");
        Person person2 = (Person) applicationContext2.getBean("person");

        System.out.println(person == person2);
    }

    @Test
    public void test04(){
        String[] beanNamesForType = applicationContext2.getBeanNamesForType(Person.class);
        Environment environment = applicationContext2.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name:beanNamesForType) {
            System.out.println(name);
        }
        Map<String, Person> beansOfType = applicationContext2.getBeansOfType(Person.class);
        System.out.println(beansOfType);


    }

    public void printBean(ApplicationContext applicationContext){
        String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
        for (String name:beanNamesForType) {
            System.out.println(name);
        }
    }

    @Test
    public void testImport(){
        printBean(applicationContext2);
        Blue blue = applicationContext2.getBean(Blue.class);
        System.out.println(blue);

    }

}
