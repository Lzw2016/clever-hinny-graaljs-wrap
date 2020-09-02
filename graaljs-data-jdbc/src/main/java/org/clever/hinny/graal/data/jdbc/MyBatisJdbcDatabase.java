package org.clever.hinny.graal.data.jdbc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/09/02 21:02 <br/>
 */
public class MyBatisJdbcDatabase {
    public static final JdbcDatabase Instance = new JdbcDatabase();

    private static final ConcurrentMap<String, MyBatisJdbcDataSource> DATASOURCE_MAP = new ConcurrentHashMap<>();

    private String defaultName;

    protected MyBatisJdbcDatabase() {
    }

}
