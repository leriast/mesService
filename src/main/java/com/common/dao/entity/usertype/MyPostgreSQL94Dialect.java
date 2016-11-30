package com.common.dao.entity.usertype;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hsqldb.types.Types;

/**
 * Created by root on 11/28/16.
 */
public class MyPostgreSQL94Dialect extends PostgreSQL94Dialect {

    public MyPostgreSQL94Dialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        //this.registerColumnType(Types.VARBINARY, "bytea");
    }
}
