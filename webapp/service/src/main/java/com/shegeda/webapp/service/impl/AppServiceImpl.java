package com.shegeda.webapp.service.impl;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.dao.app.AppDao;
import com.shegeda.webapp.service.AppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Service
@Transactional
public class AppServiceImpl implements AppService {

  private final AppDao appDao;

  @Autowired
  public AppServiceImpl(final AppDao appDao) {this.appDao = appDao;}

  @Override
  public void create(final App app) {
    appDao.save(app);
  }

  @Override
  public void update(final App app) {
    appDao.save(app);
  }

  @Override
  public void delete(final App app) {
    appDao.delete(app);
  }

  @Override
  public void deleteById(final Integer appId) {
    appDao.delete(appId);
  }

  @Override
  public App getAppById(final Integer id) {
    return appDao.getAppById(id);
  }

  @Override
  public Page<App> getAllUserApps(final Integer userId, final Pageable pageable) {
    return appDao.getAllUserApps(userId, pageable);
  }

  @Override
  public Page<App> findAllApps(final Pageable pageable) {
    return appDao.findAll(pageable);
  }

}
