package com.shegeda.webapp.service.impl;

import static com.shegeda.webapp.core.entity.app.UserRole.PUBLISHER;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.core.entity.app.User_;
import com.shegeda.webapp.dao.app.UserDao;
import com.shegeda.webapp.service.UserService;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexandr Shegeda
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  /**
   * @key - user token
   * @value - userId
   */
  private ConcurrentMap<String, Integer> userTokenIdMap = new ConcurrentHashMap<>();

  /**
   * @key - userId
   * @value - user
   */
  private ConcurrentMap<Integer, User> userMap = new ConcurrentHashMap<>();

  @Override
  public User getUserById(final Integer userId) {
    if(userMap.containsKey(userId)) {
      return userMap.get(userId);
    }
    return userDao.findOne(userId);
  }

  @Override
  public User getUserByToken(final String token) {
    User user = null;
    if(userTokenIdMap.containsKey(token)) {
      user = userMap.get(userTokenIdMap.get(token));
    }
    return user;
  }

  @Override
  public String login(final User user) {
    String token = UUID.randomUUID().toString();
    userTokenIdMap.put(token, user.getId());
    if(!userMap.containsKey(user.getId())) userMap.put(user.getId(), user);
    return token;
  }

  @Override
  public void logout(final String token) {
    Integer userId = userTokenIdMap.remove(token);
    if(!userTokenIdMap.containsValue(userId)) userMap.remove(userId);
  }

  @Override
  public void updateUser(final User user) {
    userMap.put(user.getId(), user);
    userDao.save(user);
  }

  @Override
  public User findUserByUserName(final String userName) {
    return userDao.findUserByUserName(userName);
  }

  @Override
  public boolean createPublisher(final User user) {
    User existingUser = userDao.findUserByEmail(user.getEmail());
    if(existingUser != null) return false;
    user.setRole(PUBLISHER);
    return userDao.save(user) != null;
  }
}
