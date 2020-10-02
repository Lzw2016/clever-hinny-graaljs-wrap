package org.clever.hinny.graal.meta.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.meta.data.model.TableSchema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020-10-02 11:08 <br/>
 */
@Slf4j
public class MateDataManageTest {

    private HikariDataSource dataSource;
    private MateDataManage mateDataManage;

    @Before
    public void init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://mysql.msvc.top:3306/clever-template");
        // hikariConfig.setJdbcUrl("jdbc:mysql://192.168.31.40:3306/clever-template");
        hikariConfig.setUsername("clever-template");
        hikariConfig.setPassword("lizhiwei1993");
        hikariConfig.setAutoCommit(false);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaxLifetime(1800_000);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.getDataSourceProperties().put("serverTimezone", "GMT+8");
        hikariConfig.getDataSourceProperties().put("useUnicode", "true");
        hikariConfig.getDataSourceProperties().put("characterEncoding", "utf-8");
        hikariConfig.getDataSourceProperties().put("zeroDateTimeBehavior", "convert_to_null");
        hikariConfig.getDataSourceProperties().put("useSSL", "false");
        dataSource = new HikariDataSource(hikariConfig);
        MateDataManage.Instance.setDefault("default", dataSource);
        MateDataManage.Instance.getDefault().reload();
    }

    @After
    public void close() {
        dataSource.close();
    }

    @Test
    public void t01() {
        TableSchema tableSchema = MateDataManage.Instance.getDefault().getTableSchema("clever-template", "hyb_orders");
        log.info("###tableSchema -> {}", tableSchema);
    }
}
