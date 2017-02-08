package com.shegeda.webapp.dao.app;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Alexandr Shegeda
 */
@NoRepositoryBean
public interface PublisherDao extends HibernateDAO<User> {

  User getPublisherById(Integer id);

  Page<User> findAllPublishers(Pageable pageable);

  void create(User user, App app);

  void create(User user);
}
