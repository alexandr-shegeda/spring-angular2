package com.shegeda.webapp.dao.app.impl;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.App_;
import com.shegeda.webapp.core.entity.app.User_;
import com.shegeda.webapp.dao.app.AbstractHibernateDAO;
import com.shegeda.webapp.dao.app.AppDao;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandr Shegeda
 */
@Repository
public class AppHibernateDAOImpl extends AbstractHibernateDAO<App> implements AppDao {

  @Autowired
  public AppHibernateDAOImpl(EntityManager entityManager) {
    super(App.class, entityManager);
  }

  @Override
  public App getAppById(final Integer id) {
    return findOne(id);
  }

  @Override
  public Page<App> getAllUserApps(final Integer userId, final Pageable pageable) {
    return findAll((root, criteriaQuery, builder) -> builder.equal(root.get(App_.user).get(User_.id), userId), pageable);
  }

}
