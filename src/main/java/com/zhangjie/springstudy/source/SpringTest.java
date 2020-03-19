package com.zhangjie.springstudy.source;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class SpringTest {
    public static void main(String[] args) {

        ClassPathResource resource = new ClassPathResource("beans.xml");

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(resource);

        Person person = (Person) factory.getBean("person");

        System.out.println(person.say());

    }
}
