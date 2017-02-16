package com.shegeda.webapp.dao.app.impl;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.User_;
import com.shegeda.webapp.dao.app.AbstractHibernateDAO;
import com.shegeda.webapp.dao.app.AuthUserDao;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandr Shegeda
 */
@Repository
public class AuthUserDaoImpl extends AbstractHibernateDAO<User> implements AuthUserDao {

  @Autowired
  public AuthUserDaoImpl(final EntityManager entityManager) {
    super(User.class, entityManager);
  }

  @Override
  public User findByUserName(final String username) {
    return findOne(byUserName(username));
  }

  @Override
  public User findByEmail(final String email) {
    return findOne(byEmail(email));
  }

  private static Specification<User> byUserName(String userName) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.name), userName);
  }

  private static Specification<User> byEmail(String email) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.email), email);
  }
}
