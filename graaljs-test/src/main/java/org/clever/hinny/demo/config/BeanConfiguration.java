package org.clever.hinny.demo.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.folder.FileSystemFolder;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.api.pool.EngineInstancePool;
import org.clever.hinny.api.pool.GenericEngineInstancePool;
import org.clever.hinny.graal.mvc.HttpRequestGraalScriptHandler;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/28 20:34 <br/>
 */
@Configuration
@Slf4j
public class BeanConfiguration {
//    /**
//     * 分页插件
//     */
//    @SuppressWarnings("UnnecessaryLocalVariable")
//    @Bean
//    public CustomPaginationInterceptor paginationInterceptor() {
//        CustomPaginationInterceptor paginationInterceptor = new CustomPaginationInterceptor();
//        paginationInterceptor.setSqlParser()
//        paginationInterceptor.setDialectClazz()
//        paginationInterceptor.setOverflow()
//        paginationInterceptor.setProperties();
//        return paginationInterceptor;
//    }

    /**
     * 乐观锁插件<br />
     * 取出记录时，获取当前version <br />
     * 更新时，带上这个version <br />
     * 执行更新时， set version = yourVersion+1 where version = yourVersion <br />
     * 如果version不对，就更新失败 <br />
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

//    /**
//     * 逻辑删除<br />
//     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * SQL执行效率插件
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // performanceInterceptor.setFormat(true);
        // performanceInterceptor.setMaxTime();
        // performanceInterceptor.setWriteInLog();
        return performanceInterceptor;
    }

    /**
     * 执行分析插件<br />
     * SQL 执行分析拦截器【 目前只支持 MYSQL-5.6.3 以上版本 】
     * 作用是分析 处理 DELETE UPDATE 语句
     * 防止小白或者恶意 delete update 全表操作！
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Bean
    @Profile({"dev", "test"})
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        // sqlExplainInterceptor.stopProceed
        return sqlExplainInterceptor;
    }

    @SneakyThrows
    @Bean
    public EngineInstancePool<Context, Value> engineInstancePool() {
        final String absolutePath = new File("D:\\SourceCode\\clever\\clever-hinny-js").getAbsolutePath();
        // 创建对象池配置
        GenericObjectPoolConfig<ScriptEngineInstance<Context, Value>> config = new GenericObjectPoolConfig<>();
        config.setMaxWaitMillis(-1);
        config.setMaxTotal(8);
        config.setMinIdle(2);
        // 创建对象工厂
        Folder rootFolder = FileSystemFolder.createRootPath(absolutePath);
        Engine engine = Engine.newBuilder()
                .useSystemProperties(true)
                .build();
        GraalEngineFactory factory = new GraalEngineFactory(rootFolder, engine);
        final EngineInstancePool<Context, Value> pool = new GenericEngineInstancePool<>(factory, config);
        // 监听文件变化
        FileAlterationMonitor monitor = new FileAlterationMonitor(1000 * 3);
        FileAlterationObserver observer = new FileAlterationObserver(absolutePath);
        observer.addListener(new FileAlterationListenerAdaptor() {
            @Override
            public void onDirectoryCreate(File directory) {
                onChange(directory);
            }

            @Override
            public void onDirectoryChange(File directory) {
                onChange(directory);
            }

            @Override
            public void onDirectoryDelete(File directory) {
                onChange(directory);
            }

            @Override
            public void onFileCreate(File file) {
                onChange(file);
            }

            @Override
            public void onFileChange(File file) {
                onChange(file);
            }

            @Override
            public void onFileDelete(File file) {
                onChange(file);
            }

            @SneakyThrows
            private void onChange(File fileOrDir) {
                log.debug("脚本文件变化: -> {}", fileOrDir.getAbsolutePath());
                pool.clear();
            }
        });
        monitor.addObserver(observer);
        monitor.start();
        return pool;
    }

    @Bean
    public HttpRequestGraalScriptHandler httpRequestGraalScriptHandler(EngineInstancePool<Context, Value> engineInstancePool) {
        return new HttpRequestGraalScriptHandler(engineInstancePool);
    }
}
