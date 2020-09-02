package org.clever.hinny.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.data.jdbc.JdbcDataSource;
import org.clever.hinny.data.jdbc.MyBatisJdbcDataSource;
import org.clever.hinny.data.jdbc.dynamic.MyBatisMapperSql;
import org.clever.hinny.graal.data.jdbc.JdbcDatabase;
import org.clever.hinny.graal.data.jdbc.MyBatisJdbcDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/28 21:15 <br/>
 */
@Component
@Slf4j
public class InitConfig implements CommandLineRunner {
    /**
     * 是否初始化完成
     */
    private boolean initComplete = false;

    @Autowired(required = false)
    private List<DataSource> dataSourceList;

    @Autowired
    private MyBatisMapperSql myBatisMapperSql;

    @Override
    public synchronized void run(String... args) {
        if (initComplete) {
            return;
        }
        initComplete = true;
        if (dataSourceList != null) {
            for (int i = 0; i < dataSourceList.size(); i++) {
                DataSource dataSource = dataSourceList.get(i);
                if (i == 0) {
                    JdbcDataSource ds = new JdbcDataSource(dataSource);
                    JdbcDatabase.Instance.setDefault("default", ds);

                    MyBatisJdbcDataSource mybatisDS = new MyBatisJdbcDataSource(ds, myBatisMapperSql);
                    MyBatisJdbcDatabase.Instance.setDefault("default", mybatisDS);
                    continue;
                }
                JdbcDatabase.Instance.add("jdbc-" + i, new JdbcDataSource(dataSource));
            }
        }
        log.info("初始化完成");
    }
}
