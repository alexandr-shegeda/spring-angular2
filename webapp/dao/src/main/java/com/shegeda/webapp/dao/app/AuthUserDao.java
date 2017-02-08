package com.shegeda.webapp.dao.app;

import com.shegeda.webapp.core.entity.app.User;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Alexandr Shegeda
 */
@NoRepositoryBean
public interface AuthUserDao {

  User findByUserName(final String username);
}
