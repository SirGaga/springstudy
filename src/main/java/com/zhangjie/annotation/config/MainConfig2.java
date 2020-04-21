package com.zhangjie.annotation.config;

import com.zhangjie.annotation.bean.Color;
import com.zhangjie.annotation.bean.Person;
import com.zhangjie.annotation.bean.Red;
import com.zhangjie.annotation.condition.LinuxCondition;
import com.zhangjie.annotation.condition.MyImportBeanDefinitionRegistrar;
import com.zhangjie.annotation.condition.MyImportSelect;
import com.zhangjie.annotation.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@Import({Color.class, Red.class, MyImportSelect.class, MyImportBeanDefinitionRegistrar.class})
//导入组件：id默认是组件的全类名
public class MainConfig2 {
    // @Scope注解表明的是bean的作用域
    /**
     *@see ConfigurableBeanFactory#SCOPE_PROTOTYPE 多实例,ioc容器启动并不会创建对象放到容器中，每次获取才会调用方法创建对象，每次获取都会调用创建对象
     *@see ConfigurableBeanFactory#SCOPE_SINGLETON 单实例,ioc容器启动会调用方法创建对象放到ioc容器中，以后每次获取就是从容器中拿
     *@see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST 同一次请求创建一次
     *@see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION 同一次session创建一次
     */
    // 默认是单实例的，无论多少次获取，都是我们在MainConfig2中new出来的对象
    // 懒加载：针对于单实例bean，默认在容器启动的时候创建对象，在标注了@Lazy之后容器启动不创建对象，在第一次使用bean的时候才会创建对象，并初始化
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Lazy
    @Bean("person")
    public Person person(){
        System.out.println("给容器中添加Person...");
        return new Person("zhangsan",25);
    }

    /**
     *
     *  @Conditional({Condition}):按照一定条件进行判断，满足条件给容器中注册bean,标记在类上代表满足条件，这个类中的所有bena注册才会生效，达到类中组件统一设置的目的
     *
     *  如果操作系统是windows，给容器中注册bill
     *  如果是linux则注册linus
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("Bill Gates",65);
    }
    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){
        return new Person("Linus",45);
    }

    /**
     * 给容器中注册组件的方式：
     * 1.默认使用包扫描和使用注解 @ComponentScan @Controller、@Service、@Repository、@Component，主要用于自己编写的类
     * 2.@Bean注解，主要用于第三方包中的组件
     * 3.@Import注解，快速往容器中导入一个组件
     *      1.@Import(要导入到容器中的组件)：容器中就会自动注册这个组件，ID默认是全类名
     *      2.ImportSelector：返回需要导入组件的全类名的数组；
     *      3.ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4.FactoryBean：
     */

}
