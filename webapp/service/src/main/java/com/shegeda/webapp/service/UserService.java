package com.shegeda.webapp.service;

import com.shegeda.webapp.core.entity.app.User;

/**
 * @author Alexandr Shegeda
 */
public interface UserService {

  User getUserById(Integer userId);

  User getUserByToken(String token);

  String login(User user);

  void logout(String token);

  void updateUser(User user);

  User findUserByUserName(String userName);

  boolean createPublisher(User user);
}
