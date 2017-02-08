package com.shegeda.webapp.service.impl;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.dao.app.AppDao;
import com.shegeda.webapp.dao.app.PublisherDao;
import com.shegeda.webapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Service
public class PublisherServiceImpl implements PublisherService {

  private final PublisherDao publisherDao;

  private final AppDao appDao;

  @Autowired
  public PublisherServiceImpl(final PublisherDao publisherDao, final AppDao appDao) {
    this.publisherDao = publisherDao;
    this.appDao = appDao;
  }

  @Override
  public void create(final User user) {
    publisherDao.create(user);
  }

  @Override
  public void update(final User user) {
    publisherDao.save(user);
  }

  @Override
  public void delete(final User user) {
    publisherDao.delete(user);
  }

  @Override
  public void deleteById(final Integer entityId) {
    //todo implements
  }

  @Override
  public User getPublisherById(final Integer id) {
    return publisherDao.getPublisherById(id);
  }

  @Override
  public Page<User> findAllPublishers(final Pageable pageable) {
    return publisherDao.findAllPublishers(pageable);
  }

  @Override
  public void create(final User user, final App app) {
    publisherDao.create(user, app);
  }
}
