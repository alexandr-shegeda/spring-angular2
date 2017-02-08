package com.shegeda.webapp.core.entity.app;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Alexandr Shegeda
 */
@Entity
@Table(name = "applications")
public class App implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "app_id")
  private Integer id;

  @Column
  private String name;

  @Column(name = "app_type")
  @Enumerated(EnumType.STRING)
  private AppType type;

  @ElementCollection(targetClass = ContentType.class, fetch = FetchType.EAGER)
  @JoinTable(name = "app_content_type", joinColumns = @JoinColumn(name = "app_id", nullable = false))
  @Column(name = "content_type", nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private List<ContentType> contentTypes;

  @OneToOne(mappedBy = "app", fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  public App() {
  }

  public App(final String name, final AppType type, final List<ContentType> contentTypes) {
    this.name = name;
    this.type = type;
    this.contentTypes = contentTypes;
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

  public AppType getType() {
    return type;
  }

  public void setType(final AppType type) {
    this.type = type;
  }

  public List<ContentType> getContentTypes() {
    return contentTypes;
  }

  public void setContentTypes(final List<ContentType> contentTypes) {
    this.contentTypes = contentTypes;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    App app = (App) o;
    if (id != null ? !id.equals(app.id) : app.id != null) return false;
    if (name != null ? !name.equals(app.name) : app.name != null) return false;
    return type == app.type;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}
