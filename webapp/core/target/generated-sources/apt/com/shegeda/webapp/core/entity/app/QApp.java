package com.shegeda.webapp.core.entity.app;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApp is a Querydsl query type for App
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApp extends EntityPathBase<App> {

    private static final long serialVersionUID = -1613676665L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApp app = new QApp("app");

    public final ListPath<ContentType, EnumPath<ContentType>> contentTypes = this.<ContentType, EnumPath<ContentType>>createList("contentTypes", ContentType.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final EnumPath<AppType> type = createEnum("type", AppType.class);

    public final QUser user;

    public QApp(String variable) {
        this(App.class, forVariable(variable), INITS);
    }

    public QApp(Path<? extends App> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApp(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApp(PathMetadata<?> metadata, PathInits inits) {
        this(App.class, metadata, inits);
    }

    public QApp(Class<? extends App> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

