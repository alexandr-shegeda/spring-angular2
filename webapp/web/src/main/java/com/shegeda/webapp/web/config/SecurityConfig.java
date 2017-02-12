package com.shegeda.webapp.web.config;

import static com.shegeda.webapp.core.entity.app.UserRole.ADMIN;
import static com.shegeda.webapp.core.entity.app.UserRole.ADOPS;
import static com.shegeda.webapp.core.entity.app.UserRole.PUBLISHER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Alexandr Shegeda
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("publisher").password("111").roles(PUBLISHER.name());
    auth.inMemoryAuthentication().withUser("admin").password("111").roles(ADMIN.name());
    auth.inMemoryAuthentication().withUser("operator").password("111").roles(ADOPS.name());
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .antMatchers("/welcome/**", "/").permitAll()
          .antMatchers("/operator/**").access(String.format("hasRole('%s')", ADMIN))
          .antMatchers("/publisher/**").access(String.format("hasRole('%s') or hasRole('%s')", ADMIN, ADOPS))
          .antMatchers("/app/**").access(String.format("hasRole('%s') or hasRole('%s')", ADOPS, PUBLISHER))
        .and()
        .formLogin()
          .loginPage("/login")
          .failureUrl("/login?error")
          .usernameParameter("username")
          .passwordParameter("password")
          .successForwardUrl("/auth/login")
          .permitAll()
        .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/")
          .permitAll()
        .and()
          .sessionManagement()
          .maximumSessions(5);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}