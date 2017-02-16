package com.shegeda.webapp.web.controllers;

import static com.shegeda.webapp.core.entity.app.UserRole.ANONYMOUS;

import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.service.UserService;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alexandr Shegeda
 */
@RestController
@CrossOrigin
public class WelcomeController implements AuthenticationEntryPoint {

  @Autowired
  private UserService userService;

  private List<String> inMemoryUsers = Arrays.asList("admin", "publisher", "operator");

  @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
  public ModelAndView welcomePage() {
    return new ModelAndView("main/app");
  }

  @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
  public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return new ModelAndView("redirect:/login?logout");
  }

  private SimpleGrantedAuthority getCurrentRole() {
    Collection<SimpleGrantedAuthority> authorities;
    authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    Iterator<SimpleGrantedAuthority> iter = authorities.iterator();
    if (iter.hasNext()) return iter.next();
    return null;
  }

  private UserRole getCurrentUserRole() {
    Collection<SimpleGrantedAuthority> authorities;
    authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    Iterator<SimpleGrantedAuthority> iter = authorities.iterator();
    if (iter.hasNext()) {
      String role = iter.next().getAuthority().replaceFirst("ROLE_", "");
      return UserRole.valueOf(role);
    }
    return ANONYMOUS;
  }

  @RequestMapping(value = "/api/login", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> login(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout,
                                 @CookieValue(value = "token", required = false) String userToken, HttpServletResponse response) {

    if (error != null) {
      return new ResponseEntity<>("Invalid email or password!", HttpStatus.BAD_REQUEST);
    } else if (logout != null) {
      userService.logout(userToken);
      return new ResponseEntity<>("You've been logged out successfully.", HttpStatus.OK);
    } else {
      User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String userName = userPrincipal.getUsername();
      com.shegeda.webapp.core.entity.app.User user = null;
      //fixme
      if (inMemoryUsers.contains(userName)) {
        user = new com.shegeda.webapp.core.entity.app.User(userName, String.format("%s@yopmail.com", userName), getCurrentUserRole());
        user.setId(-5);
      } else {
        user = userService.findUserByUserName(userName);
      }
      String token = userService.login(user);
      response.addCookie(new Cookie("token", token));
      return new ResponseEntity(HttpStatus.OK);
    }
  }

  @RequestMapping(value = "/api/register", method = RequestMethod.POST)
  public ResponseEntity registration(@RequestBody com.shegeda.webapp.core.entity.app.User user) {
    if(userService.createPublisher(user)) {
      return new ResponseEntity<>("User created", HttpStatus.OK);
    }
    return new ResponseEntity<>("User with such email already exist", HttpStatus.BAD_REQUEST);

  }

  @Override
  public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException e)
  throws IOException, ServletException {
    response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
  }
}
