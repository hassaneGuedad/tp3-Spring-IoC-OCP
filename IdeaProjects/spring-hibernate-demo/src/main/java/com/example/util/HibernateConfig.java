package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.entities", "com.example.metier"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {

    @Value("${spring.datasource.driver-class-name}") private String driverClassName;
    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String username;
    @Value("${spring.datasource.password}") private String password;
    @Value("${spring.jpa.properties.hibernate.dialect}") private String hibernateDialect;
    @Value("${spring.jpa.hibernate.ddl-auto}") private String hibernateDdlAuto;
    @Value("${spring.jpa.show-sql}") private String showSql;
    @Value("${spring.jpa.properties.hibernate.format_sql}") private String formatSql;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.entities");

        Properties props = new Properties();
        props.put("hibernate.dialect", hibernateDialect);
        props.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        props.put("hibernate.show_sql", showSql);
        props.put("hibernate.format_sql", formatSql);

        sessionFactory.setHibernateProperties(props);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
}
