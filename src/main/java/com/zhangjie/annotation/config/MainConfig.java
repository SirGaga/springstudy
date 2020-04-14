package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Person;
import com.zhangjie.annotation.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

// 配置类就等同于配置文件
@Configuration // 告诉Spring 这是一个配置类
@ComponentScan(value = "com.zhangjie.annotation",
//        includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})
//},
//        excludeFilters = {
        includeFilters = {
//        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class, Service.class}),
//        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {MyTypeFilter.class})
},useDefaultFilters = false)
// 包扫描，只要标注了@Controller、@Service、@Repository、@Component
// 只要被标注了这四个中的任意一个，都会被扫描并被加载进容器中
// value指定要扫描的包
// excludeFilters代表扫描的时候排除按照什么规则排除哪些组件
// includeFilters代表扫描的时候需要按照什么规则包含哪些组件
// 优先级excludeFilters > includeFilters
// useDefaultFilters代表是否禁用掉默认的扫描规则即扫描带有@Controller、@Service、@Repository、@Component这四个注解的组件
// FilterType.ANNOTATION代表过滤规则是按照注解来进行过滤
// FilterType.ASSIGNABLE_TYPE代表过滤规则是按照指定的类型类进行过滤
// FilterType.ASPECTJ代表按照AspectJ表达式的规则进行过滤
// FilterType.REGEX代表按照正则表达式的规则进行过滤
// FilterType.CUSTOM代表按照自定义的规则进行过滤
public class MainConfig {

    @Bean("person01")
    // 给容器中注册一个bean，类型为返回值的类型，id默认使用方法名,
    // 通过给@Bean指定value也可以获取到bean的id，就是你指定的value的值
    public Person person(){
        return new Person("lisi",18);
    }

}
