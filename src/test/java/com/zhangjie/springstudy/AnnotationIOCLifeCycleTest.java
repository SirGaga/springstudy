package com.zhangjie.springstudy;

import com.zhangjie.annotation.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationIOCLifeCycleTest {

    @Test
    public void test01(){
        // 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");
        // 关闭容器，创建的bean会被销毁
        applicationContext.close();


    }

}
