package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Car;
import com.zhangjie.annotation.bean.Color;
import com.zhangjie.annotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *      Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 *      1.@Autowired:自动注入，
 *      默认按照 类型 在容器中查找需要的组件 applicationContext.getBean(BookDao.class) ，找到后赋值
 *      如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找 applicationContext.getBean("bookDao")
 *      2.@Qualifier("bookDao2")，使用@Qualifier指定需要装配的组件id，而不是使用属性名
 *      3.自动装配默认一定要将属性赋值好或者赋值完成，否则没有就会报错，可以使用@Autowired(required=false)
 *      4.@Primary，让Spring自动装配的时候默认使用首选的bean,也可以结合@Qulifier注解手选指定需要装配的bean的名字
 *      public class BookService {
 *          @Autowired
 *          private BookDao bookDao;
 *      }
 * Spring还支持 @Resource 注解（JSP-250）和 @Inject(JSP-330),都是JAVA规范注解
 *      @Resource：可以和@AutoWired一样实现自动装配功能，默认按照组件名称进行装配的,
 *      没有能支持@Primary的功能，没有支持@Autowired(required=false)
 *      @Inject：需要外部的Maven依赖javax.inject的包，和@AutoWired的功能一样，但是不支持@Autowired(required=false)
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能的
 * @Autowired：可以在构造器、参数、方法、属性上都可以标注这个注解
 *      1）方法上：主要是setter方法上,用的最多的还是@Bean注解+方法参数：参数从容器中获取，默认不写@Autowired，但是效果是一样的，都能自动装配
 *      2）构造器：一般是有参构造器，如果组件只有一个有参构造器，
 *              这个有参构造器的Autowired可以省略，参数位置的组件可以自动从容器中获取
 *      3）参数上
 *      不管在哪里标注，他们都是从容器中获取参数组件的值
 * 自定义组件想要使用Spring容器底层的一些组件（ApplicationContext,BeanFactory等等）
 *      自定义组件实现XXXAware:在创建对象的时候，会调用接口规定的方法注入相关的组件
 *      把Spring底层的一些组件注入到自定义的Bean中
 *      XXXAware：功能是使用XXXProcessor来进行处理：
 *
 *
 *
 *
 *
 */
@Configuration
@ComponentScan({"com.zhangjie.annotation.dao","com.zhangjie.annotation.service",
        "com.zhangjie.annotation.controller","com.zhangjie.annotation.bean"})
public class MainConfigAutoWired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    /**
     * @Bean标注的方法创建对象的时候，方法参数的值从容器中获取
     * @param car
     * @return
     */
    @Bean
    public Color getColor(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
