package com.shegeda.webapp.web.controllers;

import static com.shegeda.webapp.core.entity.app.UserRole.PUBLISHER;
import static com.shegeda.webapp.web.RestUtils.convertFromDTO;
import static com.shegeda.webapp.web.RestUtils.encodePassword;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.service.PublisherService;
import com.shegeda.webapp.core.entity.dto.UserDTO;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private PublisherService publisherService;

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView getCreateOperatorForm() {
    ModelAndView modelAndView = new ModelAndView("publishers/create");
    modelAndView.addObject("publisher", new User(PUBLISHER));
    return modelAndView;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> createOperator(@RequestBody User publisher) {
    publisher.setRole(PUBLISHER);
    encodePassword(publisher);
    publisherService.create(publisher);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Page<User>> getAllOperators(Pageable pageable) {
    Page<User> publishers = publisherService.findAllPublishers(pageable);
    return new ResponseEntity<>(publishers, HttpStatus.OK);
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ResponseEntity<UserDTO> getOperatorById(@PathVariable Integer id) {
    User publisher = publisherService.getPublisherById(id);
    return new ResponseEntity<>(new UserDTO(publisher), HttpStatus.OK);
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public ResponseEntity<HttpStatus> editOperator(@PathVariable Integer id, @RequestBody UserDTO publisherDTO) {
    User publisher = publisherService.getPublisherById(id);
    if (publisherDTO.isChangePassword()) {
      encodePassword(publisherDTO);
      if (!Arrays.equals(publisher.getPassword(), publisherDTO.getOldPassword())) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(HttpStatus.UNAUTHORIZED);
      }
      publisher.setPassword(publisherDTO.getPassword());
    }
    convertFromDTO(publisher, publisherDTO);
    publisherService.update(publisher);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ResponseEntity<HttpStatus> deleteOperator(@PathVariable Integer id) {
    publisherService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
