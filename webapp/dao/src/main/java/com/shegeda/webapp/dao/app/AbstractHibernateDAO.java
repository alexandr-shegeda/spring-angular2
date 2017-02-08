package com.shegeda.webapp.dao.app;

import com.shegeda.webapp.core.entity.app.User;
import com.shegeda.webapp.core.entity.app.UserRole;
import com.shegeda.webapp.core.entity.app.User_;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Alexandr Shegeda
 */
@NoRepositoryBean
public abstract class AbstractHibernateDAO<T> extends SimpleJpaRepository<T, Integer> implements HibernateDAO<T> {

  public AbstractHibernateDAO(final Class<T> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }

  public static Specification<User> userHasRole(UserRole role) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.role), role);
  }
}
