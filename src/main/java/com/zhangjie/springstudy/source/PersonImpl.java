package com.zhangjie.springstudy.source;

public class PersonImpl implements Person{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String say() {
        if(null == name){
            return "zhangsan";
        }
        return "Hello,"+name;
    }
}
