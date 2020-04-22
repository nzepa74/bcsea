package com.ngawang.zepa.helper;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Created by USER on 10/14/2019.
 */
public class MySQLCustomDialectG2C extends MySQL5Dialect {
    public MySQLCustomDialectG2C() {
        super();
        registerHibernateType(-9, "string");
    }
}

