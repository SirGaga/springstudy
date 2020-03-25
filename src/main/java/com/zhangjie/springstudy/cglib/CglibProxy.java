package com.zhangjie.springstudy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class<?> clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        Object object = enhancer.create();

        return object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println(o.getClass().getName()+ "." + method.getName());

        // 执行父类的对应方法
        Object result = methodProxy.invokeSuper(o,objects);

        System.out.println("执行结束！");


        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Person person = (Person) proxy.getProxy(Person.class);

        System.out.println(person.getClass().getName());

        System.out.println(person.getClass().getSuperclass().getName());

        person.study();
    }
}
