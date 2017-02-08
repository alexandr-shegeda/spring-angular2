package com.shegeda.webapp.core.entity.app;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(App.class)
public abstract class App_ {

	public static volatile SingularAttribute<App, String> name;
	public static volatile SingularAttribute<App, Integer> id;
	public static volatile SingularAttribute<App, AppType> type;
	public static volatile ListAttribute<App, ContentType> contentTypes;
	public static volatile SingularAttribute<App, User> user;

}

