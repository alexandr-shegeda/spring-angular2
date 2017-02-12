package com.shegeda.webapp.dao.app.impl;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.User_;
import com.shegeda.webapp.dao.app.UserDao;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Repository
@Transactional
public class UserDaoImpl extends SimpleJpaRepository<User, Integer> implements UserDao {

  private EntityManager entityManager;

  @Autowired
  public UserDaoImpl(final EntityManager entityManager) {
    super(User.class, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  public User findUserByUserName(final String userName) {
    return findOne(userByName(userName));
  }

  @Override
  public User findUserByEmail(final String email) {
    return findOne(userByEMail(email));
  }

  private Specification<User> userByName(String userName) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.name), userName);
  }

  private Specification<User> userByEMail(String email) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.email), email);
  }
}
