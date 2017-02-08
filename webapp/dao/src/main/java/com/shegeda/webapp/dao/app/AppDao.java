package com.shegeda.webapp.dao.app;

import com.shegeda.webapp.core.entity.app.App;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Alexandr Shegeda
 */
@NoRepositoryBean
public interface AppDao extends HibernateDAO<App> {

  App getAppById(Integer id);

  Page<App> getAllUserApps(Integer userId, Pageable pageable);

}
