package org.clever.hinny.graal.data.jdbc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.clever.common.model.request.QueryByPage;
import org.clever.common.model.request.QueryBySort;
import org.clever.hinny.graaljs.utils.InteropScriptToJavaUtils;
import org.graalvm.polyglot.Value;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/29 12:51 <br/>
 */
public class JdbcDataSource {
    private static final String Order_Field_Name = "orderField";
    private static final String Sort_Name = "sort";
    private static final String Order_Fields_Name = "orderFields";
    private static final String Sorts_Name = "sorts";
    private static final String Fields_Mapping_Name = "fieldsMapping";
    private static final String Page_Size_Name = "pageSize";
    private static final String Page_No_Name = "pageNo";
    private static final String Is_Search_Count_Name = "isSearchCount";

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

    /**
     * 查询多条数据(大量数据)，使用游标读取
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     * @param consumer 游标读取数据消费者
     */
    public void query(String sql, Map<String, Object> paramMap, Value consumer) {
        Assert.isTrue(consumer != null && consumer.canExecute(), "参数consumer必须是回调函数");
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        delegate.query(sql, paramMap, consumer::executeVoid);
    }

    /**
     * 查询多条数据(大量数据)，使用游标读取
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param consumer 游标读取数据消费者
     */
    public void query(String sql, Value consumer) {
        Assert.isTrue(consumer != null && consumer.canExecute(), "参数consumer必须是回调函数");
        delegate.query(sql, consumer::executeVoid);
    }

    /**
     * 排序查询
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param sortMap  排序配置
     * @param paramMap 参数，参数格式[:param]
     */
    public List<Map<String, Object>> queryBySort(String sql, Map<String, Object> sortMap, Map<String, Object> paramMap) {
        sortMap = InteropScriptToJavaUtils.Instance.convertMap(sortMap);
        QueryBySort sort = getQueryBySort(sortMap, null);
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryBySort(sql, sort, paramMap);
    }

    /**
     * 排序查询
     *
     * @param sql     sql脚本，参数格式[:param]
     * @param sortMap 排序配置
     */
    public List<Map<String, Object>> queryBySort(String sql, Map<String, Object> sortMap) {
        sortMap = InteropScriptToJavaUtils.Instance.convertMap(sortMap);
        QueryBySort sort = getQueryBySort(sortMap, null);
        return delegate.queryBySort(sql, sort);
    }

    /**
     * 分页查询(支持排序)，返回分页对象
     *
     * @param sql           sql脚本，参数格式[:param]
     * @param paginationMap 分页配置(支持排序)
     * @param paramMap      参数，参数格式[:param]
     * @param countQuery    是否要执行count查询(可选)
     */
    public IPage<Map<String, Object>> queryByPage(String sql, Map<String, Object> paginationMap, Map<String, Object> paramMap, boolean countQuery) {
        paginationMap = InteropScriptToJavaUtils.Instance.convertMap(paginationMap);
        QueryByPage pagination = getQueryByPage(paginationMap);
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.queryByPage(sql, pagination, paramMap, countQuery);
    }

    /**
     * 分页查询(支持排序)，返回分页对象
     *
     * @param sql           sql脚本，参数格式[:param]
     * @param paginationMap 分页配置(支持排序)
     * @param countQuery    是否要执行count查询(可选)
     */
    public IPage<Map<String, Object>> queryByPage(String sql, Map<String, Object> paginationMap, boolean countQuery) {
        paginationMap = InteropScriptToJavaUtils.Instance.convertMap(paginationMap);
        QueryByPage pagination = getQueryByPage(paginationMap);
        return delegate.queryByPage(sql, pagination, countQuery);
    }

    /**
     * 分页查询(支持排序)，返回分页对象
     *
     * @param sql           sql脚本，参数格式[:param]
     * @param paginationMap 分页配置(支持排序)
     */
    public IPage<Map<String, Object>> queryByPage(String sql, Map<String, Object> paginationMap) {
        paginationMap = InteropScriptToJavaUtils.Instance.convertMap(paginationMap);
        QueryByPage pagination = getQueryByPage(paginationMap);
        return delegate.queryByPage(sql, pagination);
    }




    // --------------------------------------------------------------------------------------------
    // Update 操作
    // --------------------------------------------------------------------------------------------


    private QueryByPage getQueryByPage(Map<String, Object> paginationMap) {
        QueryByPage queryByPage = new QueryByPage();
        getQueryBySort(paginationMap, queryByPage);
        Object pageSize = paginationMap.get(Page_Size_Name);
        Object pageNo = paginationMap.get(Page_No_Name);
        Object isSearchCount = paginationMap.get(Is_Search_Count_Name);
        Assert.isTrue(pageSize == null || pageSize instanceof Number, "参数pageSize必须是一个数组");
        Assert.isTrue(pageNo == null || pageNo instanceof Number, "参数pageNo必须是一个数组");
        Assert.isTrue(isSearchCount == null || isSearchCount instanceof Boolean, "参数isSearchCount必须是一个Boolean值");
        if (pageSize != null) {
            queryByPage.setPageSize((Integer) pageSize);
        }
        if (pageNo != null) {
            queryByPage.setPageNo((Integer) pageNo);
        }
        if (isSearchCount != null) {
            queryByPage.setSearchCount((Boolean) isSearchCount);
        }
        return queryByPage;
    }

    private QueryBySort getQueryBySort(Map<String, Object> sortMap, QueryBySort queryBySort) {
        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            Object object = entry.getValue();
            entry.setValue(InteropScriptToJavaUtils.Instance.toJavaObject(object));
        }
        Object orderField = sortMap.get(Order_Field_Name);
        Object sort = sortMap.get(Sort_Name);
        Object orderFields = sortMap.get(Order_Fields_Name);
        Object sorts = sortMap.get(Sorts_Name);
        Object fieldsMapping = sortMap.get(Fields_Mapping_Name);
        Assert.isTrue(orderField == null || orderField instanceof String, "参数orderField必须是一个字符串");
        Assert.isTrue(sort == null || sort instanceof String, "参数sort必须是一个字符串");
        Assert.isTrue(orderFields == null || orderFields instanceof String[], "参数orderFields必须是一个字符串数组");
        Assert.isTrue(sorts == null || sorts instanceof String[], "参数sorts必须是一个字符串数组");
        Assert.isTrue(fieldsMapping == null || fieldsMapping instanceof Map, "参数sorts必须是一个字符串数组");
        // 排序参数转换
        if (queryBySort == null) {
            queryBySort = new QueryBySort();
        }
        queryBySort.setOrderField((String) orderField);
        queryBySort.setSort((String) sort);
        queryBySort.setOrderFields(orderFields == null ? null : Arrays.asList((String[]) orderFields));
        queryBySort.setSorts(sorts == null ? null : Arrays.asList((String[]) sorts));
        if (fieldsMapping != null) {
            Map<?, ?> map = (Map<?, ?>) fieldsMapping;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                queryBySort.addOrderFieldMapping(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        return queryBySort;
    }
}
