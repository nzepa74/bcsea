package com.ngawang.zepa.helper;

import org.hibernate.dialect.MySQLDialect;

/**
 * Created by N-Zepa on 16-Jun-18.
 */
public class MySQLCustomDialect extends MySQLDialect {
    //region public method
    public MySQLCustomDialect() {
        super();
        registerHibernateType(-9, "string");
        registerHibernateType(0, "integer");
    }
    //endregion
}