package com.shegeda.webapp.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author Alexandr Shegeda
 */
@Component
public class SpringApplicationContext implements BeanFactoryAware {

  private static BeanFactory springBeanFactory;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    springBeanFactory = beanFactory;
  }

  public static <T> T getBean(Class<? extends T> clazz) {
    return springBeanFactory.getBean(clazz);
  }

}
