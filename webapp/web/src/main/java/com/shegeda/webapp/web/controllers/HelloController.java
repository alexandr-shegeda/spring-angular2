package com.shegeda.webapp.web.controllers;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alexandr Shegeda
 */
@Controller
public class HelloController {

  @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
  public ModelAndView welcomePage(ModelAndView model) {
    Collection<SimpleGrantedAuthority> authorities;
    authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext()
                                                                            .getAuthentication()
                                                                            .getAuthorities();
    model.addObject("title", "Spring Security Hello World");
    model.addObject("message", "This is welcome page!");
    model.setViewName("hello");
    model.addObject("role", getCurrentRole(authorities));
    return model;

  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
  }

  private SimpleGrantedAuthority getCurrentRole(final Collection<SimpleGrantedAuthority> authorities) {
    Iterator<SimpleGrantedAuthority> iter = authorities.iterator();
    if (iter.hasNext()) return iter.next();
    return null;
  }

  @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "Invalid username and password!");
    }
    if (logout != null) {
      model.addObject("msg", "You've been logged out successfully.");
    }
    model.setViewName("login");
    return model;
  }
}
