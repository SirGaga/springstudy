package com.zhangjie.springstudy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Properties;

public class IOCTest {

    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
    private ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("ioc2.xml");
    private ApplicationContext applicationContext3 = new ClassPathXmlApplicationContext("ioc3.xml");
    /**
     * 从容器中拿到这个组件
     */
    @Test
    public void test() {
        // ApplicationContext:代表IOC容器
        // ClassPathXmlApplicationContext：标识当前应用的xml配置文件在ClassPath下
        // 根据Spring的配置文件得到ioc容器对象
        // 容器中对象的创建是在创建容器完成时就被创建了
        // 同一个组件在一个IOC容器中是单实例的，只有一份
        // 容器中如果没有相应的组件，报错
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'person03' available
        // IOC容器在创建这个组件对象的时候，(property标签)会利用setter方法为JavaBean属性进行赋值
        // getter/setter方法是属性名，setter方法去掉set之后的小写首字母名称就是属性名
//        ApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("ioc.xml");

        // 调用该方法容器就会帮我们创建好对象
        Person person = (Person) applicationContext.getBean("person01");

        Person person2 = (Person) applicationContext.getBean("person01");
        // 经测试没有问题
        System.out.println(person);

        System.out.println(person == person2);

//        Person person3 = (Person) applicationContext.getBean("person03");
    }

    /**
     * 根据bean的类型从IOC容器中获取bean实例
     * 如果ioc容器中这个类型的bean有多个，查找会报错
     * 可以用getBean(String name, Class<T> requiredType)这个方法来进行查找，
     * 指定bean的id和确定要返回的bean的类型
     * 当然也可以直接用getBean(String name)来进行查找，不过找到之后要进行对象的类型转换
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'com.zhangjie.springstudy.Person' available:
     * expected single matching bean but found 2: person01,person02
     */
    @Test
    public void test02() {

//        Person person = applicationContext.getBean(Person.class);
//
//        System.out.println(person);
        Person person = applicationContext.getBean("person02",Person.class);
        System.out.println(person);

        Person person1 = (Person) applicationContext.getBean("person03");

        System.out.println(person1);
        Person person6 = (Person) applicationContext.getBean("person06");

        System.out.println(person6);

    }

    /**
     * 正确的为各种属性赋值
     * 测试使用null值、默认引用类型就是null；基本类型是默认值
     */
    @Test
    public void test03(){
        Person person = applicationContext2.getBean("person01",Person.class);
        System.out.println(person);

        Person person2 = applicationContext2.getBean("person02",Person.class);
        System.out.println(person2);
        Map<String, Object> map = person2.getMap();
        System.out.println(map);

        Properties properties = person2.getProperties();
        System.out.println(properties);

    }

    @Test
    public void test04(){
        Person person2 = applicationContext2.getBean("person03",Person.class);
        Map<String, Object> map = person2.getMap();
        System.out.println(map);

        Person person4 = applicationContext2.getBean("person04",Person.class);
        Car car = person4.getCar();
        System.out.println(car);


        Person person6 = applicationContext2.getBean("person06",Person.class);
        System.out.println(person6);


    }
    @Test
    public void  test08(){

    }
}