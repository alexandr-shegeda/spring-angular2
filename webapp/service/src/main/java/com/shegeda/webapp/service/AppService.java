package com.shegeda.webapp.service;

import com.shegeda.webapp.core.entity.app.App;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexandr Shegeda
 */
public interface AppService extends BasicService<App> {

  App getAppById(Integer id);

  Page<App> getAllUserApps(Integer userId, Pageable pageable);

  Page<App> findAllApps(Pageable pageable);

}
