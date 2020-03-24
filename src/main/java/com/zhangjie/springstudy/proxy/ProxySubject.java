package com.zhangjie.springstudy.proxy;

public class ProxySubject extends Subject {

    private RealSubject realSubject;

    public void request(){
        this.preRequest();
        if(null == realSubject){
            realSubject = new RealSubject();

        }
        realSubject.request();

        this.postRequest();
    }

    private void preRequest(){
        System.out.println("pre Request");
    }

    private void postRequest(){
        System.out.println("post Request");
    }

}
