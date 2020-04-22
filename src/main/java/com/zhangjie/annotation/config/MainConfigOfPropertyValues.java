package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/person.properties")
// 使用@PropertySource注解读取外部配置文件中的k-v保存到运行的环境变量中，加载完外部的配置文件以后使用${}取出配置文件中的值4
public class MainConfigOfPropertyValues {
    @Bean
    public Person person(){
        return new Person();
    }

}
