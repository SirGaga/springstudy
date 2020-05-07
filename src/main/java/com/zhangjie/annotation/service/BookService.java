package com.zhangjie.annotation.service;

import com.zhangjie.annotation.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BookService {
    //@Qualifier("bookDao")
    //@Autowired
    //@Resource(name="bookDao2")
    @Inject
    private BookDao bookDao;
    public void print(){
        System.out.println(bookDao);
    }

    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
