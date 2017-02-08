package com.shegeda.webapp.web.controllers;

import static com.shegeda.webapp.core.entity.app.UserRole.ADOPS;
import static com.shegeda.webapp.web.RestUtils.convertFromDTO;
import static com.shegeda.webapp.web.RestUtils.encodePassword;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.service.AdOpsService;
import com.shegeda.webapp.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexandr Shegeda
 */
@RestController
@CrossOrigin
@RequestMapping("/operator")
public class OperatorController {

  @Autowired
  private AdOpsService adOpsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView getCreateOperatorForm() {
    ModelAndView modelAndView = new ModelAndView("operators/create");
    modelAndView.addObject("operator", new User());
    return modelAndView;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView createOperator(@ModelAttribute User operator) {
    operator.setRole(ADOPS);
    encodePassword(operator);
    adOpsService.create(operator);
    return new ModelAndView("redirect:/operator");
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Page<User>> getAllOperators(Pageable pageable, HttpServletRequest request) {
//    ModelAndView modelAndView = new ModelAndView("operators/getAll");
//    modelAndView.addObject("operators", adOpsService.findAll(pageable));
//    return modelAndView;
    return new ResponseEntity<>(adOpsService.findAllOperators(pageable), HttpStatus.OK);
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView getOperatorById(@PathVariable Integer id) {
    ModelAndView modelAndView = new ModelAndView("operators/edit");
    User operator = adOpsService.getAdOpsById(id);
    modelAndView.addObject("user", new UserDTO(operator));
    return modelAndView;
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> editOperator(@PathVariable Integer id, @ModelAttribute UserDTO operatorDTO) {
    User operator = adOpsService.getAdOpsById(id);
    if(operatorDTO.isChangePassword()) {
      encodePassword(operatorDTO);
      if(!operator.getPassword().equals(operatorDTO.getOldPassword())) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(HttpStatus.NOT_MODIFIED);
      operator.setPassword(operatorDTO.getPassword());
    }
    convertFromDTO(operator, operatorDTO);
    adOpsService.update(operator);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ResponseEntity<HttpStatus> deleteOperator(@PathVariable Integer id) {
    adOpsService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
