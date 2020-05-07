package com.zhangjie.annotation.dao;

import org.springframework.stereotype.Repository;
//在容器中注册的bean的id默认是类名首字母小写
// BookDao -> bookDao
@Repository
public class BookDao {

    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
