package com.shegeda.webapp.service;

/**
 * @author Alexandr Shegeda
 */
public interface BasicService<T> {

  void create(T entity);

  void update(T entity);

  void delete(T entity);

  void deleteById(Integer entityId);

}
