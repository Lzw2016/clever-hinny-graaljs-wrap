package org.clever.hinny.graal.data.jdbc;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

import java.io.Serializable;

@Data
public class JdbcInfo implements Serializable {
    private String driverClassName;

    private String jdbcUrl;

    private boolean isAutoCommit;

    private boolean isReadOnly;

    private DbType dbType;
}
