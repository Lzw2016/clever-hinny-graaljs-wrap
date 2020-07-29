package org.clever.hinny.graal.data.jdbc;

import lombok.Data;

import java.io.Serializable;
import java.util.Properties;

@Data
public class JdbcConfig implements Serializable {

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

    private boolean isAutoCommit;

    private boolean isReadOnly;

    private int maxPoolSize;

    private int minIdle;

    private long maxLifetimeMs;

    private long connectionTimeoutMs;

    private long idleTimeoutMs;

    private String connectionTestQuery;

    private Properties dataSourceProperties;
}
