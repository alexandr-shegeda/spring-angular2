package com.shegeda.webapp.service.impl;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.dao.app.AdOpsDao;
import com.shegeda.webapp.service.AdOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Service
@Transactional
public class AdOpsServiceImpl implements AdOpsService {

  private final AdOpsDao adOpsDao;

  @Autowired
  public AdOpsServiceImpl(final AdOpsDao adOpsDao) {this.adOpsDao = adOpsDao;}

  @Override
  public User getAdOpsById(final Integer id) {
    return adOpsDao.getAdOpsById(id);
  }

  @Override
  public Page<User> findAllOperators(final Pageable pageable) {
    return adOpsDao.findAllOperators(pageable);
  }

  @Override
  public void create(final User user) {
    adOpsDao.save(user);
  }

  @Override
  public void update(final User user) {
    adOpsDao.save(user);
  }

  @Override
  public void delete(final User user) {
    adOpsDao.delete(user);
  }

  @Override
  public void deleteById(final Integer adOpsById) {
    delete(getAdOpsById(adOpsById));
  }
}
