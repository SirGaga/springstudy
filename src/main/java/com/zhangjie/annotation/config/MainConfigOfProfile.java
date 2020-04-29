package com.zhangjie.annotation.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhangjie.annotation.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile:
 *  Spring 为我们提供的可以当前环境，动态的激活和切换一系列bean（组件）的功能
 *
 *  开发环境、测试环境、生产环境
 * @Profile : 指定组件在那个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *  加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中，默认是default环境（@Profile("default")）
 *  写在配置类上只有是指定的环境的时候，整个配置类里面的所有配置才能生效
 *  没有标注环境标识的bean都是会被加载的
 */
//@Profile("test")
@Configuration
@PropertySource("classpath:/dbconfig.properties")
public class MainConfigOfProfile {
    @Value("${db.user}")
    private String user;
    //@Value("${db.password}")
    //private String password;
    @Value("${db.driverClass}")
    private String driverClass;

    @Profile("test")
    @Bean
    public Yellow yellow(){
        return new Yellow();
    }

    /**
     * 跟上边注释的结果是一样的，不同的写法而已
     * @param password
     * @return
     * @throws PropertyVetoException
     */
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

        return null;
    }
    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm_crud");
        return null;
    }
    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/scw_0515");
        return null;
    }

}
