package com.zhangjie.springstudy;

import com.zhangjie.annotation.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class AnnotationIOCProfile {
    // 1.使用命令行动态参数：在虚拟机参数位置加载  -Dspring.profiles.active=test
    // 2.使用代码的形式激活环境
    @Test
    public void test01(){
        //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        // 2-1 创建一个IoC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2-2 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        // 2-3 注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 2-4 启动刷新容器
        applicationContext.refresh();


        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : namesForType){
            System.out.println(name);
        }

        applicationContext.close();
    }

}
