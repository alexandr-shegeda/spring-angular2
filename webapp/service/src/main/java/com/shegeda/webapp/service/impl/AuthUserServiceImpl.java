package com.shegeda.webapp.service.impl;

import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.dao.app.AuthUserDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Service
public class AuthUserServiceImpl implements UserDetailsService {

  private final AuthUserDao authUserDao;

  @Autowired
  public AuthUserServiceImpl(final AuthUserDao authUserDao) {this.authUserDao = authUserDao;}

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    com.shegeda.webapp.core.entity.app.User user = authUserDao.findByUserName(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
    return buildUserForAuthentication(user, authorities);
  }

  private User buildUserForAuthentication(com.shegeda.webapp.core.entity.app.User user, List<GrantedAuthority> authorities) {
    return new User(user.getName(), new String(user.getPassword()), authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(UserRole role) {
    return Collections.singletonList(new SimpleGrantedAuthority(role.getRole()));
  }
}
