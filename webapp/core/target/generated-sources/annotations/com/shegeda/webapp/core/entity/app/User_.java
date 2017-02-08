package com.shegeda.webapp.core.entity.app;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, App> app;
	public static volatile SingularAttribute<User, char[]> password;
	public static volatile SingularAttribute<User, UserRole> role;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> email;

}

