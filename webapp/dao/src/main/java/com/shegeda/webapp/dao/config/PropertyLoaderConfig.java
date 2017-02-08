package com.shegeda.webapp.dao.config;

import java.io.InputStream;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * @author Alexandr Shegeda
 */
@Configuration
@ComponentScan(basePackages = "com.shegeda.webapp.dao")
public class PropertyLoaderConfig {

  @Bean
  public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
    PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
    InputStream file = PropertyLoaderConfig.class.getClassLoader().getResourceAsStream("hibernate.properties");
    Resource conf = new InputStreamResource(file);
    ppc.setLocations(new Resource[]{conf});
    ppc.setIgnoreUnresolvablePlaceholders(true);
    return ppc;
  }
}
