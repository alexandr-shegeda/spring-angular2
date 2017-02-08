package com.shegeda.webapp.web.controllers;

import com.shegeda.webapp.core.entity.app.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alexandr Shegeda
 */
@RestController
@RequestMapping("/publisher")
public class PublisherController {

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView createNewPublisherForm() {
    ModelAndView modelAndView = new ModelAndView("createPublisher");
    modelAndView.addObject("user", new User());
    return modelAndView;
  }

  @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
  public ModelAndView createPublisher(@ModelAttribute User user) {
    ModelAndView modelAndView = new ModelAndView();
    System.out.println(user);
    return modelAndView;
  }

}
