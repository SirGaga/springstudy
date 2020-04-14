package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 配置类就等同于配置文件
@Configuration // 告诉Spring 这是一个配置类
@ComponentScan
// 包扫描，只要标注了@Controller、@Service、@Repository、@Component
// 只要被标注了这四个中的任意一个，都会被扫描并被加载进容器中
// value指定要扫描的包
public class MainConfig {

    @Bean("person01")
    // 给容器中注册一个bean，类型为返回值的类型，id默认使用方法名,
    // 通过给@Bean指定value也可以获取到bean的id，就是你指定的value的值
    public Person person(){
        return new Person("lisi",18);
    }

}
