package org.clever.hinny.graal.data.jdbc;


import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/29 12:50 <br/>
 */
public class JdbcDatabase {
    public static final JdbcDatabase Instance = new JdbcDatabase();

    private static final ConcurrentMap<String, JdbcDataSource> DATASOURCE_MAP = new ConcurrentHashMap<>();

    public String defaultName;

    protected JdbcDatabase() {
    }

    /**
     * 设置默认数据源
     *
     * @param defaultName 默认数据源名称
     * @return 默认数据源对象
     */
    public JdbcDataSource setDefault(String defaultName) {
        this.defaultName = defaultName;
        return getDefault();
    }

    /**
     * 设置默认数据源
     *
     * @param defaultName    默认数据源名称
     * @param jdbcDataSource 默认数据源对象
     */
    public void setDefault(String defaultName, org.clever.hinny.data.jdbc.JdbcDataSource jdbcDataSource) {
        this.defaultName = defaultName;
        add(defaultName, jdbcDataSource);
    }

    /**
     * 获取默认数据源
     */
    public JdbcDataSource getDefault() {
        return getDataSource(defaultName);
    }

    /**
     * 获取默认数据源名称
     */
    public String getDefaultName() {
        return defaultName;
    }

    /**
     * 根据名称获取数据源
     *
     * @param name 数据源名称
     */
    public JdbcDataSource getDataSource(String name) {
        return DATASOURCE_MAP.get(name);
    }

    /**
     * 判断数据源是否存在
     *
     * @param name 数据源名称
     */
    public boolean hasDataSource(String name) {
        return DATASOURCE_MAP.containsKey(name);
    }

    /**
     * 添加数据源
     *
     * @param name           数据源名称
     * @param jdbcDataSource 数据源
     */
    public void add(String name, org.clever.hinny.data.jdbc.JdbcDataSource jdbcDataSource) {
        Assert.notNull(jdbcDataSource, "参数jdbcDataSource不能为空");
        Assert.isTrue(!DATASOURCE_MAP.containsKey(name), "数据源已经存在");
        DATASOURCE_MAP.put(name, new JdbcDataSource(jdbcDataSource));
    }

    /**
     * 删除数据源
     *
     * @param name 数据源名称
     */
    @SneakyThrows
    public boolean del(String name) {
        JdbcDataSource jdbcDataSource = DATASOURCE_MAP.get(name);
        if (jdbcDataSource != null) {
            jdbcDataSource.close();
            DATASOURCE_MAP.remove(name);
        }
        return jdbcDataSource != null;
    }

    // list add(config) listInfo
}
