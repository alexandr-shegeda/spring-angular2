package com.shegeda.webapp.web.controllers;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.service.AppService;
import com.shegeda.webapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexandr Shegeda
 */
@RestController
@CrossOrigin
@RequestMapping("/app")
public class AppController {

  @Autowired
  private AppService appService;

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Page<App>> getAllApps(Pageable pageable) {
    Page<App> allApps = appService.findAllApps(pageable);
    return new ResponseEntity<>(allApps, HttpStatus.OK);
  }

  @RequestMapping(value = "{userId}", method = RequestMethod.GET)
  public ResponseEntity<Page<App>> getUserApps(@PathVariable Integer userId, Pageable pageable) {
    Page<App> allUserApps = appService.getAllUserApps(userId, pageable);
    return new ResponseEntity<>(allUserApps, HttpStatus.OK);
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ResponseEntity<HttpStatus> createApp(HttpServletRequest request) {
    //    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //    request.getSession().setAttribute("user", principal);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> createApp(@RequestBody App app, @CookieValue("token") String userToken, @RequestParam Integer userId) {
    User user = userService.getUserById(userId);
    app.setUser(user);
    user.setApp(app);
    appService.create(app);
    userService.updateUser(user);
    System.out.printf("App created by user with id= %d", userService.getUserByToken(userToken).getId());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/edit/{appId}", method = RequestMethod.GET)
  public ResponseEntity<App> editApp(@PathVariable Integer appId) {
    App app = appService.getAppById(appId);
    return new ResponseEntity<>(app, HttpStatus.OK);
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> editApp(@RequestBody App app) {
    appService.update(app);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/delete/{appId}", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> deleteApp(@PathVariable Integer appId) {
    appService.deleteById(appId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
