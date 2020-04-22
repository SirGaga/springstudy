package com.zhangjie.annotation.config;

import com.zhangjie.annotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *      Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 *      1.@AutoWired:自动注入，
 *      默认按照 类型 在容器中查找需要的组件 applicationContext.getBean(BookDao.class) ，找到后赋值
 *      如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找 applicationContext.getBean("bookDao")
 *      2.@Qualifier("bookDao2")，使用@Qualifier指定需要装配的组件id，而不是使用属性名
 *      3.自动装配默认一定要将属性赋值好或者赋值完成，否则没有就会报错，可以使用@AutoWired(required=false)
 *      4.@Primary，让Spring自动装配的时候默认使用首选的bean,也可以结合@Qulifier注解手选指定需要装配的bean的名字
 *      public class BookService {
 *          @Autowired
 *          private BookDao bookDao;
 *      }
 * Spring还支持 @Resource 注解（JSP-250）和 @Inject(JSP-330),都是JAVA规范注解
 *      @Resource：可以和@AutoWired一样实现自动装配功能，默认按照组件名称进行装配的,
 *      没有能支持@Primary的功能，没有支持@AutoWired(required=false)
 *      @Inject：需要外部的Maven依赖javax.inject的包，和@AutoWired的功能一样，但是不支持@AutoWired(required=false)
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能的
 * @AutoWired：可以在构造器、参数、方法、属性上都可以标注这个注解
 *
 *
 */
@Configuration
@ComponentScan({"com.zhangjie.annotation.dao","com.zhangjie.annotation.service","com.zhangjie.annotation.controller"})
public class MainConfigAutoWired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
}
