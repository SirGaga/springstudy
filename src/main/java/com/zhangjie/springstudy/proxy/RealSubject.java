package com.zhangjie.springstudy.proxy;

public class RealSubject extends Subject{

    public void request() {
        System.out.println("From real subject.");
    }

}
