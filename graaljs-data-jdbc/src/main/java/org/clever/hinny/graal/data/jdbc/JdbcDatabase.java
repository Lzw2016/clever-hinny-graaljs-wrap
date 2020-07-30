package org.clever.hinny.graal.data.jdbc;


import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/29 12:50 <br/>
 */
public class JdbcDatabase {
    public static final JdbcDatabase Instance = new JdbcDatabase();

    private static final ConcurrentMap<String, JdbcDataSource> DATASOURCE_MAP = new ConcurrentHashMap<>();

    private String defaultName;

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
     * 添加数据源
     *
     * @param name       数据源名称
     * @param jdbcConfig 数据源配置
     */
    public JdbcDataSource add(String name, JdbcConfig jdbcConfig) {
        // TODO 添加数据源
        return null;
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

    /**
     * 删除所有数据源
     */
    @SneakyThrows
    public void delAll() {
        Iterator<Map.Entry<String, JdbcDataSource>> iterator = DATASOURCE_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, JdbcDataSource> entry = iterator.next();
            entry.getValue().close();
            iterator.remove();
        }
    }

    /**
     * 获取所有数据源名称
     */
    public Set<String> allNames() {
        return DATASOURCE_MAP.keySet();
    }

    /**
     * 获取数据源信息
     *
     * @param name 数据源名称
     */
    public JdbcInfo getInfo(String name) {
        // TODO 获取数据源信息
        return null;
    }

    /**
     * 获取所有数据源信息
     */
    public Map<String, JdbcInfo> allInfos() {
        // TODO 获取所有数据库信息
        return null;
    }
}
