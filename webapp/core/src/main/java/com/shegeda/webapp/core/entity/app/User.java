package com.shegeda.webapp.core.entity.app;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 * @author Alexandr Shegeda
 */
@Entity
@Table(name = "users")
@SecondaryTable(name = "user_details")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Integer id;
  @Column
  private String name;
  @Column
  private String email;
  @Column
  @Enumerated(EnumType.STRING)
  private UserRole role;
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private App app;

  @Column(table = "user_details", nullable = false)
  private char[] password;

  @Column(table = "user_details")
  private String phone;

  public User() {
  }

  public User(UserRole role) {
    this.role = role;
  }

  public User(final String name, final String email, final UserRole role, final char[] password) {
    this.name = name;
    this.email = email;
    this.role = role;
    this.password = password;
  }

  public User(final String name, final String email, final UserRole role, final App app) {
    this.name = name;
    this.email = email;
    this.role = role;
    this.app = app;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(final UserRole role) {
    this.role = role;
  }

  public App getApp() {
    return app;
  }

  public void setApp(final App app) {
    this.app = app;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(final char[] password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", role=" + role + ", app=" + app + '}';
  }
}
