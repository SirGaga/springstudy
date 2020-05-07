package com.zhangjie.annotation.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//默认加载IoC容器中的组件，容器启动会调用无参构造器，创建对象，再进行初始化赋值操作
@Component
public class Boss {

    private Car car;

    //@Autowired
    //构造器要用的组件都是从容器中获取
    public Boss(@Autowired Car car){
        this.car = car;
        System.out.println("boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }


    //@Autowired
    //标注在方法上，Spring创建当前对象的时候，就会调用方法完成赋值
    //方法使用的参数，自定义类型的值从ioc容器中进行获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
