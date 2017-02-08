package com.shegeda.webapp.web;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.web.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Alexandr Shegeda
 */
public class RestUtils {

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public static char[] encodePassword(final User operator) {
    char[] encoded = passwordEncoder.encode(operator.getEmail() + new String(operator.getPassword())).toCharArray();
    operator.setPassword(encoded);
    return encoded;
  }

  public static char[] encodePassword(final UserDTO operator) {
    char[] encoded = passwordEncoder.encode(operator.getEmail() + new String(operator.getPassword())).toCharArray();
    operator.setPassword(encoded);
    return encoded;
  }

  public static void convertFromDTO(User user, UserDTO dto) {
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
  }
}
