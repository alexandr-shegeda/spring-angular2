package com.shegeda.webapp.service;

import com.shegeda.webapp.core.entity.app.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexandr Shegeda
 */
public interface AdOpsService extends BasicService<User> {

  User getAdOpsById(Integer id);

  Page<User> findAllOperators(Pageable pageable);
}
