package com.shegeda.webapp.dao.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Alexandr Shegeda
 */
@NoRepositoryBean
public interface HibernateDAO<T> extends JpaRepository<T, Integer> {

}
