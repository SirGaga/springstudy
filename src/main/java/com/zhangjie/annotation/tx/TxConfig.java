package com.zhangjie.annotation.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事务：
 *
 * 环境搭建：
 * 1、导入相关依赖
 *      数据源、数据库驱动、SpringJdbc模块
 * 2、配置数据源和JdbcTemplate操作数据库
 * 3、在方法上标注 @Transactional 表示当前方法是事务方法
 * 4、@EnableTransactionManagement 开启基于注解的事务管理功能
 * 5、配置事务管理器管理事务
 *
 * 原理：
 * 1) @EnableTransactionManagement 利用 TransactionManagementConfigurationSelector 给容器中导入组件
 *      导入两个组件：AutoProxyRegistrar 和 ProxyTransactionManagementConfiguration
 * 2) AutoProxyRegistrar ：给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
 *      InfrastructureAdvisorAutoProxyCreator : 利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（其中包含增强器），
 *          代理对象利用拦截器链进行调用
 *
 * 3) ProxyTransactionManagementConfiguration
 *
 */
@EnableTransactionManagement
@PropertySource("classpath:/dbconfig.properties")
@Configuration
@ComponentScan(value = {"com.zhangjie.annotation.service","com.zhangjie.annotation.dao"})
public class TxConfig {

    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.driverClass}")
    private String driverClass;
    @Value("${db.jdbcUrl}")
    private String jdbcUrl;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);

        return dataSource;
    }

    /**
     * Spring 对 @Configuration 类会特殊处理，给容器中添加组件的方法，多次调用，都只是在容器中查找组件，并不会多次创建，组件仍旧是单实例
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
    //在容器中注册事务管理器
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
