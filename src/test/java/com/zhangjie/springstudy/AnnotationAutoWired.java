package com.zhangjie.springstudy;

import com.zhangjie.annotation.config.MainConfigAutoWired;
import com.zhangjie.annotation.dao.BookDao;
import com.zhangjie.annotation.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationAutoWired {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAutoWired.class);
        BookService bookService = applicationContext.getBean(BookService.class);

        System.out.println(bookService);
        BookDao bookDao = applicationContext.getBean(BookDao.class);

        System.out.println(bookDao);

    }
}
