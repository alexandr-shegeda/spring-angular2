package com.shegeda.webapp.web.initializer;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * @author Alexandr Shegeda
 */
public class ServletInitializer extends AbstractDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.scan(ClassUtils.getPackageName(getClass()));
    return context;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null;
  }

}
