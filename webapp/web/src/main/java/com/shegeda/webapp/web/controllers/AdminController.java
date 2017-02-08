package com.shegeda.webapp.web.controllers;

import static com.shegeda.webapp.core.entity.app.UserRole.ADOPS;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.service.AdOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alexandr Shegeda
 */
@RestController(value = "/admin")
public class AdminController {

//  @Autowired
//  private AdOpsService adOpsService;

//  @RequestMapping(value = "/operator/create", method = RequestMethod.GET)
//  public ModelAndView createOperator() {
//    ModelAndView modelAndView = new ModelAndView("operators/create");
//    modelAndView.addObject("operator", new User(ADOPS));
//    return modelAndView;
//  }
//
//  @RequestMapping(value = "/operator", method = RequestMethod.GET)
//  public ModelAndView getAllOperators() {
//    ModelAndView modelAndView = new ModelAndView("operators/getAll");
//    modelAndView.addObject("operators", adOpsService.getAllAdOps());
//    return modelAndView;
//  }
//
//  @RequestMapping(value = "/operator/{id}", method = RequestMethod.GET)
//  public ModelAndView getOperatorById(@PathVariable Long id) {
//    ModelAndView modelAndView = new ModelAndView("operators/edit");
//    modelAndView.addObject("operator", adOpsService.getAdOpsById(id));
//    return modelAndView;
//  }
//
//  @RequestMapping(value = "/operator/edit", method = {RequestMethod.POST, RequestMethod.GET})
//  public ResponseEntity<HttpStatus> editOperator(@ModelAttribute User operator) {
//    adOpsService.update(operator);
//    return new ResponseEntity<>(HttpStatus.OK);
//  }
//
//  @RequestMapping(value = "/operator/delete", method = {RequestMethod.POST, RequestMethod.GET})
//  public ResponseEntity<HttpStatus> deleteOperator(@ModelAttribute User operator) {
//    adOpsService.delete(operator);
//    return new ResponseEntity<>(HttpStatus.OK);
//  }

}
