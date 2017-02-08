package com.shegeda.webapp.service;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexandr Shegeda
 */
public interface PublisherService extends BasicService<User> {

  User getPublisherById(Integer id);

  Page<User> findAllPublishers(Pageable pageable);

  void create(User user, App app);

}
