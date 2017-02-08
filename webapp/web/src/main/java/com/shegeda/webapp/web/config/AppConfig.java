package com.shegeda.webapp.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Alexandr Shegeda
 */
@Configuration
@ComponentScan(basePackages = "com.shegeda.webapp")
@Import({SecurityConfig.class})
public class AppConfig {

}
