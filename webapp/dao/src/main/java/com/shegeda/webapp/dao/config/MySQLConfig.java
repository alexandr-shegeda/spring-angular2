package com.shegeda.webapp.dao.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Alexandr Shegeda
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.shegeda.webapp.core.entity.app")
@ComponentScan(basePackages = "com.shegeda.webapp.dao")
public class MySQLConfig {

  @Value("${jdbc.driverClassName}")
  private String driverClassName;
  @Value("${jdbc.url}")
  private String url;
  @Value("${jdbc.username}")
  private String username;
  @Value("${jdbc.password}")
  private String password;

  @Value("${hibernate.dialect}")
  private String hibernateDialect;
  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;
  @Value("${hibernate.format_sql}")
  private String hibernateFormatSql;
  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateHbm2ddlAuto;
  @Value("${hibernate.ejb.naming_strategy}")
  private String hibernateEjbNamingStrategy;

  private static final String ENTITY_PACKAGE = "com.shegeda.webapp.core.entity.app";

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource(url, username, password);
    ds.setDriverClassName(driverClassName);
    Properties properties = new Properties();
    properties.put("minPoolSize", "1");
    properties.put("maxPoolSize", "100");
    properties.put("breakAfterAcquireFailure", "false");
    properties.put("acquireRetryAttempts", "3");
    properties.put("idleConnectionTestPeriod", "300");
    properties.put("testConnectionOnCheckout", "true");
    ds.setConnectionProperties(properties);
    return ds;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource());
    factory.setJpaVendorAdapter(vendorAdapter());
    factory.setJpaDialect(new HibernateJpaDialect());
    factory.setJpaProperties(hibernateProperties());
    factory.setPackagesToScan(ENTITY_PACKAGE);
    factory.afterPropertiesSet();
    return factory;
  }

  @Bean
  public HibernateJpaVendorAdapter vendorAdapter() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(false);
    vendorAdapter.setDatabase(Database.MYSQL);
    vendorAdapter.setShowSql(true);
    vendorAdapter.setDatabasePlatform(MySQL5Dialect.class.getName());
    return vendorAdapter;
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    transactionManager.afterPropertiesSet();
    return transactionManager;
  }

  @Bean
  public Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", hibernateDialect);
    properties.put("hibernate.show_sql", hibernateShowSql);
    properties.put("hibernate.format_sql", hibernateFormatSql);
    properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
    properties.put("hibernate.ejb.naming_strategy", hibernateEjbNamingStrategy);
    return properties;
  }

}
