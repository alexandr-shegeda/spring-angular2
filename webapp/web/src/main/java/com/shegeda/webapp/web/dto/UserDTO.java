package com.shegeda.webapp.web.dto;

import com.shegeda.webapp.core.entity.app.User;

/**
 * @author Alexandr Shegeda
 */
public class UserDTO {

  private Integer id;
  private String name;
  private char[] oldPassword;
  private char[] password;
  private String email;
  private boolean changePassword;

  public UserDTO() {
  }

  public UserDTO(final User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
  }

  public boolean isChangePassword() {
    return changePassword;
  }

  public void setChangePassword(final boolean changePassword) {
    this.changePassword = changePassword;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public char[] getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(final char[] oldPassword) {
    this.oldPassword = oldPassword;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(final char[] password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }
}
