package com.shegeda.webapp.dao.app.impl;

import static com.shegeda.webapp.core.entity.app.UserRole.ADOPS;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.dao.app.AbstractHibernateDAO;
import com.shegeda.webapp.dao.app.AdOpsDao;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AdOpsHibernateDAOImpl extends AbstractHibernateDAO<User> implements AdOpsDao {

  private EntityManager entityManager;

  @Autowired
  public AdOpsHibernateDAOImpl(final EntityManager entityManager) {
    super(User.class, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  public User getAdOpsById(final Integer id) {
    return findOne(id);
  }

  @Override
  public Page<User> findAllOperators(final Pageable pageable) {
    return findAll(userHasRole(ADOPS), pageable);
  }

  @Override
  public void create(final User user) {
    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();
  }

}
