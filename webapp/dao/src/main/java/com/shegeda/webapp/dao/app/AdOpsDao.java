package com.shegeda.webapp.dao.app;

import com.shegeda.webapp.core.entity.app.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexandr Shegeda
 */
public interface AdOpsDao extends HibernateDAO<User>, JpaRepository<User, Integer> {

  User getAdOpsById(Integer id);

  Page<User> findAllOperators(Pageable pageable);

  void create(User user);

}
