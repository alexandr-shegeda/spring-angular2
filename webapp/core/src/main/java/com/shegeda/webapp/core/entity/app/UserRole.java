package com.shegeda.webapp.core.entity.app;

/**
 * @author Alexandr Shegeda
 */
public enum UserRole {
  ADMIN("ROLE_ADMIN"), ADOPS("ROLE_ADOPS"), PUBLISHER("ROLE_PUBLISHER"), ANONYMOUS("ROLE_ANONYMOUS");

  private String role;

  UserRole(final String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  @Override
  public String toString() {
    return this.role;
  }
}
