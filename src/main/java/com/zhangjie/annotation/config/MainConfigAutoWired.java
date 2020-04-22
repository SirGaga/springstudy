package com.zhangjie.annotation.config;

import com.zhangjie.annotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配：
 *      Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 *      1.@AutoWired:自动注入，
 *      默认按照 类型 在容器中查找需要的组件 applicationContext.getBean(BookDao.class) ，找到后赋值
 *      如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找 applicationContext.getBean("bookDao")
 *      2.@Qualifier("bookDao2")
 *      public class BookService {
 *          @Autowired
 *          private BookDao bookDao;
 *      }
 */
@Configuration
@ComponentScan({"com.zhangjie.annotation.dao","com.zhangjie.annotation.service","com.zhangjie.annotation.controller"})
public class MainConfigAutoWired {

    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
}
