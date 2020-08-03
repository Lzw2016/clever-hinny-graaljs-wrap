package org.clever.hinny.graal.data.jdbc;

import com.baomidou.mybatisplus.annotation.DbType;
import org.clever.hinny.graaljs.utils.InteropScriptToJavaUtils;
import org.graalvm.polyglot.Value;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/29 12:51 <br/>
 */
public class JdbcDataSource {
    private final org.clever.hinny.data.jdbc.JdbcDataSource delegate;

    public JdbcDataSource(org.clever.hinny.data.jdbc.JdbcDataSource delegate) {
        this.delegate = delegate;
    }

    /**
     * 获取数据库类型
     */
    public DbType getDbType() {
        return delegate.getDbType();
    }

    /**
     * 当前数据源是否关闭
     */
    public boolean isClosed() {
        return delegate.isClosed();
    }

    /**
     * 关闭当前数据源
     */
    public void close() throws Exception {
        delegate.close();
    }

    // --------------------------------------------------------------------------------------------
    // Query 操作
    // --------------------------------------------------------------------------------------------

    /**
     * 查询一条数据，返回一个Map
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public Map<String, Object> queryMap(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryMap(sql, paramMap);
    }

    /**
     * 查询多条数据，返回一个Map数组
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public List<Map<String, Object>> queryList(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryList(sql, paramMap);
    }

    /**
     * 查询多条数据，返回一个Map数组
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public List<Map<String, Object>> queryList(String sql) {
        return delegate.queryList(sql);
    }

    /**
     * 查询返回一个 String
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public String queryString(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryString(sql, paramMap);
    }

    /**
     * 查询返回一个 String
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public String queryString(String sql) {
        return delegate.queryString(sql);
    }

    /**
     * 查询返回一个 Long
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public Long queryLong(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryLong(sql, paramMap);
    }

    /**
     * 查询返回一个 Long
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public Long queryLong(String sql) {
        return delegate.queryLong(sql);
    }

    /**
     * 查询返回一个 Double
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public Double queryDouble(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryDouble(sql, paramMap);
    }


    /**
     * 查询返回一个 Double
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public Double queryDouble(String sql) {
        return delegate.queryDouble(sql);
    }

    /**
     * 查询返回一个 BigDecimal
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public BigDecimal queryBigDecimal(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryBigDecimal(sql, paramMap);
    }

    /**
     * 查询返回一个 BigDecimal
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public BigDecimal queryBigDecimal(String sql) {
        return delegate.queryBigDecimal(sql);
    }

    /**
     * 查询返回一个 Boolean
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public Boolean queryBoolean(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryBoolean(sql, paramMap);
    }

    /**
     * 查询返回一个 Boolean
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public Boolean queryBoolean(String sql) {
        return delegate.queryBoolean(sql);
    }

    /**
     * 查询返回一个 Date
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public Date queryDate(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryDate(sql, paramMap);
    }

    /**
     * 查询返回一个 Date
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public Date queryDate(String sql) {
        return delegate.queryDate(sql);
    }

    /**
     * SQL Count(获取一个SQL返回的数据总量)
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public long queryCount(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryCount(sql, paramMap);
    }

    /**
     * 查询多条数据(大量数据)，使用游标读取
     *
     * @param sql       sql脚本，参数格式[:param]
     * @param paramMap  参数(可选)，参数格式[:param]
     * @param batchSize 一个批次的数据量
     * @param consumer  游标批次读取数据消费者
     */
    public void query(String sql, Map<String, Object> paramMap, int batchSize, Value consumer) {
        Assert.isTrue(consumer != null && consumer.canExecute(), "参数consumer必须是回调函数");
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        delegate.query(sql, paramMap, batchSize, consumer::executeVoid);
    }

    /**
     * 查询多条数据(大量数据)，使用游标读取
     *
     * @param sql       sql脚本，参数格式[:param]
     * @param batchSize 一个批次的数据量
     * @param consumer  游标批次读取数据消费者
     */
    public void query(String sql, int batchSize, Value consumer) {
        delegate.query(sql, batchSize, consumer::executeVoid);
    }


}
