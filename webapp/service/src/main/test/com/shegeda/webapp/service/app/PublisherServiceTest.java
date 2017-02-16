package com.shegeda.webapp.service.app;

import static com.shegeda.webapp.core.entity.app.UserRole.ADMIN;
import static com.shegeda.webapp.core.entity.app.UserRole.ADOPS;
import static com.shegeda.webapp.core.entity.app.UserRole.PUBLISHER;
import static org.junit.Assert.assertTrue;

import com.shegeda.webapp.core.entity.app.App;
import com.shegeda.webapp.core.entity.app.AppType;
import com.shegeda.webapp.core.entity.app.ContentType;
import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.service.AdOpsService;
import com.shegeda.webapp.service.AppService;
import com.shegeda.webapp.service.PublisherService;
import com.shegeda.webapp.service.UserService;
import com.shegeda.webapp.service.config.ServiceConfig;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author Alexandr Shegeda
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class PublisherServiceTest {

  private MockMvc mvc;

  @Autowired
  private PublisherService publisherService;

  @Autowired
  private AppService appService;

  @Autowired
  private UserService userService;

//  @Autowired
//  private WebApplicationContext webApplicationContext;

  @Autowired
  private AdOpsService adOpsService;

//  @Before
//  public void setup() throws Exception {
//    this.mvc = webAppContextSetup(webApplicationContext).build();
//  }

  @Test
  public void testSelectAdOps() {
//    createUsers();
    
    PageRequest requestFirstTen = new PageRequest(1, 10);
    Page<User> firstTenOperators = adOpsService.findAllOperators(requestFirstTen);
    System.out.println(firstTenOperators.getSize());
    System.out.println(firstTenOperators.getTotalElements());
    System.out.println(firstTenOperators.getTotalPages());
    Page<UserDTO> users = firstTenOperators.map(this::converter);
    List<User> content = firstTenOperators.getContent();
    System.out.println(content);

  }

  private void createUsers() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    char[] def = encoder.encode("111").toCharArray();

    for (int i = 0; i < 5; i++) {
      adOpsService.create(new User(String.format("User%d", i), String.format("user%d@yopmail.com", i), PUBLISHER, def));
    }

    for (int i = 5; i < 10; i++) {
      adOpsService.create(new User(String.format("User%d", i), String.format("user%d@yopmail.com", i), ADMIN, def));
    }

    for (int i = 10; i < 20; i++) {
      adOpsService.create(new User(String.format("User%d", i), String.format("user%d@yopmail.com", i), ADOPS, def));
    }

    for (int i = 20; i < 25; i++) {
      adOpsService.create(new User(String.format("User%d", i), String.format("user%d@yopmail.com", i), PUBLISHER, def));
    }
  }

  public UserDTO converter(User user) {
    return new UserDTO(user.getName(), user.getEmail());
  }

  private class UserDTO {

    private String name;
    private String email;

    public UserDTO(final String name, final String email) {
      this.name = name;
      this.email = email;
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
  }

  @Test
  public void test() {
    App app = new App("Some app", AppType.WEBSITE, Arrays.asList(ContentType.IMAGE, ContentType.VIDEO));

    User publisher = new User("Alex", "alex@yopmail.com", UserRole.PUBLISHER, app);
    app.setUser(publisher);

    publisherService.create(publisher, app);

//    publisher.setApp(app);
//    publisherService.update(publisher);

    Page publishers = publisherService.findAllPublishers(new PageRequest(1, 20));
    Page apps = appService.findAllApps(new PageRequest(1, 20));
//    assertTrue(publishers.(publisher));
//    assertTrue(apps.contains(app));
  }

//  @Test
//  public void readSingleBookmark() throws Exception {
//    mvc.perform(get("/" + userName + "/bookmarks/"
//                        + this.bookmarkList.get(0).getId()))
//           .andExpect(status().isOk())
//           .andExpect(content().contentType(contentType))
//           .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
//           .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
//           .andExpect(jsonPath("$.description", is("A description")));
//  }


  @Test
  public void findUserByNameTest() {
    String email = "publisher1@yopmail.com";
    User userByUserName = userService.findUserByEmail(email);
    assertThat(userByUserName, notNullValue());
  }



}
