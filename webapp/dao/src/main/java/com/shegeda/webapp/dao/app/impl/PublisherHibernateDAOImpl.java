package com.shegeda.webapp.dao.app.impl;

import static com.shegeda.webapp.core.entity.app.UserRole.PUBLISHER;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.dao.app.AbstractHibernateDAO;
import com.shegeda.webapp.dao.app.AppDao;
import com.shegeda.webapp.dao.app.PublisherDao;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandr Shegeda
 */
@Repository(value = "publisherDao")
public class PublisherHibernateDAOImpl extends AbstractHibernateDAO<User> implements PublisherDao {

  @Autowired
  private AppDao appDao;

  @Autowired
  public PublisherHibernateDAOImpl(EntityManager entityManager) {
    super(User.class, entityManager);
  }

  @Override
  public User getPublisherById(final Integer id) {
    return findOne(id);
  }

  @Override
  public Page<User> findAllPublishers(final Pageable pageable) {
    return findAll(userHasRole(PUBLISHER), pageable);
  }

  @Override
  public void create(final User user, final App app) {
    save(user);
    appDao.save(app);
  }

  @Override
  public void create(final User user) {
    save(user);
  }

}
