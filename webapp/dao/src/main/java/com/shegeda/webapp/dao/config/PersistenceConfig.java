package com.shegeda.webapp.dao.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexandr Shegeda
 */
@Configuration
public class PersistenceConfig {

  @PersistenceContext
  private EntityManager em;

  @Bean
  public EntityManager entityManager() {
    return em;
  }
}
