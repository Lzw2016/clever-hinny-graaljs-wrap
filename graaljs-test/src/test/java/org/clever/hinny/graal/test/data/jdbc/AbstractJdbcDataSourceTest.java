package org.clever.hinny.graal.test.data.jdbc;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.ScriptObject;
import org.clever.hinny.api.folder.FileSystemFolder;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.graal.data.jdbc.JdbcDatabase;
import org.clever.hinny.graaljs.GraalScriptEngineInstance;
import org.graalvm.polyglot.Engine;
import org.junit.After;

import java.io.File;
import java.io.IOException;

@Slf4j
public abstract class AbstractJdbcDataSourceTest {
    // yz公司路径
    //protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("C:\\Users\\mlrs\\Desktop\\clever-hinny-js").getAbsolutePath());
    // yz笔记本路径
    //protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("C:\\Users\\ymx\\Desktop\\clever-hinny-js").getAbsolutePath());
    // lzw家里的路径
    protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("D:\\SourceCode\\clever\\clever-hinny-js").getAbsolutePath());
    protected ScriptEngineInstance<?, ?> engineInstance;
    protected ScriptObject<?> scriptObject;

    public void before() throws Exception {
        // clever-hinny-graaljs
        log.info("### rootFolder -> {}", rootFolder);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //hikariConfig.setJdbcUrl("jdbc:mysql://mysql.msvc.top:3306/clever-template");
        hikariConfig.setJdbcUrl("jdbc:mysql://192.168.31.40:3306/clever-template");
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
        org.clever.hinny.data.jdbc.JdbcDataSource jdbcDataSource = new org.clever.hinny.data.jdbc.JdbcDataSource(hikariConfig);
        JdbcDatabase.Instance.setDefault("Default", jdbcDataSource);

        Engine engine = Engine.newBuilder()
                .useSystemProperties(true)
                .build();
        engineInstance = GraalScriptEngineInstance.Builder.create(engine, rootFolder)
                .putContextMap("jdbcDatabase", JdbcDatabase.Instance)
                .build();
    }

    @After
    public void after() throws IOException {
        engineInstance.close();
        JdbcDatabase.Instance.delAll();
    }
}
