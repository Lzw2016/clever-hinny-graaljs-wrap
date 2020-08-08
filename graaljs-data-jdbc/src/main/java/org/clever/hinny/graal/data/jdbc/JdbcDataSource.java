package org.clever.hinny.graal.data.jdbc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.clever.common.model.request.QueryByPage;
import org.clever.common.model.request.QueryBySort;
import org.clever.hinny.data.jdbc.support.InsertResult;
import org.clever.hinny.data.jdbc.support.JdbcDataSourceStatus;
import org.clever.hinny.data.jdbc.support.JdbcInfo;
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
        Assert.isTrue(consumer != null && consumer.canExecute(), "参数consumer必须是回调函数");
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

    /**
     * 执行更新SQL，返回更新影响数据量
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public int update(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.update(sql, paramMap);
    }

    /**
     * 执行更新SQL，返回更新影响数据量
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public int update(String sql) {
        return delegate.update(sql);
    }

    /**
     * 更新数据库表数据
     *
     * @param tableName         表名称
     * @param fields            更新字段值
     * @param whereMap          更新条件字段(只支持=，and条件)
     * @param camelToUnderscore 字段驼峰转下划线(可选)
     */
    public int updateTable(String tableName, Map<String, Object> fields, Map<String, Object> whereMap, boolean camelToUnderscore) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        whereMap = InteropScriptToJavaUtils.Instance.convertMap(whereMap);
        return delegate.updateTable(tableName, fields, whereMap, camelToUnderscore);
    }

    /**
     * 更新数据库表数据
     *
     * @param tableName 表名称
     * @param fields    更新字段值
     * @param whereMap  更新条件字段(只支持=，and条件)
     */
    public int updateTable(String tableName, Map<String, Object> fields, Map<String, Object> whereMap) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        whereMap = InteropScriptToJavaUtils.Instance.convertMap(whereMap);
        return delegate.updateTable(tableName, fields, whereMap);
    }

    /**
     * 更新数据库表数据
     *
     * @param tableName         表名称
     * @param fields            更新字段值
     * @param where             自定义where条件(不用写where关键字)
     * @param camelToUnderscore 字段驼峰转下划线(可选)
     */
    public int updateTable(String tableName, Map<String, Object> fields, String where, boolean camelToUnderscore) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        return delegate.updateTable(tableName, fields, where, camelToUnderscore);
    }

    /**
     * 更新数据库表数据
     *
     * @param tableName 表名称
     * @param fields    更新字段值
     * @param where     自定义where条件(不用写where关键字)
     */
    public int updateTable(String tableName, Map<String, Object> fields, String where) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        return delegate.updateTable(tableName, fields, where);
    }

    /**
     * 批量执行更新SQL，返回更新影响数据量
     *
     * @param sql          sql脚本，参数格式[:param]
     * @param paramMapList 参数数组，参数格式[:param]
     */
    public int[] batchUpdate(String sql, List<Map<String, Object>> paramMapList) {
        paramMapList = InteropScriptToJavaUtils.Instance.convertMapList(paramMapList);
        return delegate.batchUpdate(sql, paramMapList);
    }

    // --------------------------------------------------------------------------------------------
    // Insert 操作
    // --------------------------------------------------------------------------------------------

    /**
     * 执行insert SQL，返回数据库自增主键值和新增数据量
     *
     * @param sql      sql脚本，参数格式[:param]
     * @param paramMap 参数(可选)，参数格式[:param]
     */
    public InsertResult insert(String sql, Map<String, Object> paramMap) {
        paramMap = InteropScriptToJavaUtils.Instance.convertMap(paramMap);
        return delegate.insert(sql, paramMap);
    }

    /**
     * 执行insert SQL，返回数据库自增主键值和新增数据量
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public InsertResult insert(String sql) {
        return delegate.insert(sql);
    }

    /**
     * 数据插入到表
     *
     * @param tableName         表名称
     * @param fields            字段名
     * @param camelToUnderscore 字段驼峰转下划线(可选)
     */
    public InsertResult insertTable(String tableName, Map<String, Object> fields, boolean camelToUnderscore) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        return delegate.insertTable(tableName, fields, camelToUnderscore);
    }

    /**
     * 数据插入到表
     *
     * @param tableName 表名称
     * @param fields    字段名
     */
    public InsertResult insertTable(String tableName, Map<String, Object> fields) {
        fields = InteropScriptToJavaUtils.Instance.convertMap(fields);
        return delegate.insertTable(tableName, fields);
    }

    /**
     * 数据插入到表
     *
     * @param tableName         表名称
     * @param fieldsList        字段名集合
     * @param camelToUnderscore 字段驼峰转下划线(可选)
     */
    public List<InsertResult> insertTables(String tableName, List<Map<String, Object>> fieldsList, boolean camelToUnderscore) {
        fieldsList = InteropScriptToJavaUtils.Instance.convertMapList(fieldsList);
        return delegate.insertTables(tableName, fieldsList, camelToUnderscore);
    }

    /**
     * 数据插入到表
     *
     * @param tableName  表名称
     * @param fieldsList 字段名集合
     */
    public List<InsertResult> insertTables(String tableName, List<Map<String, Object>> fieldsList) {
        fieldsList = InteropScriptToJavaUtils.Instance.convertMapList(fieldsList);
        return delegate.insertTables(tableName, fieldsList);
    }

    // --------------------------------------------------------------------------------------------
    //  事务操作
    // --------------------------------------------------------------------------------------------

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @param timeout             设置事务超时时间，-1表示不超时(单位：秒)
     * @param isolationLevel      设置事务隔离级别 {@link org.springframework.transaction.TransactionDefinition#ISOLATION_DEFAULT}
     * @param readOnly            设置事务是否只读
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginTX(Value action, int propagationBehavior, int timeout, int isolationLevel, boolean readOnly) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginTX(action::execute, propagationBehavior, timeout, isolationLevel, readOnly);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @param timeout             设置事务超时时间(单位：秒)
     * @param isolationLevel      设置事务隔离级别 {@link org.springframework.transaction.TransactionDefinition#ISOLATION_DEFAULT}
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginTX(Value action, int propagationBehavior, int timeout, int isolationLevel) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginTX(action::execute, propagationBehavior, timeout, isolationLevel);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @param timeout             设置事务超时时间(单位：秒)
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginTX(Value action, int propagationBehavior, int timeout) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginTX(action::execute, propagationBehavior, timeout);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginTX(Value action, int propagationBehavior) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginTX(action::execute, propagationBehavior);
    }

    /**
     * 在事务内支持操作
     *
     * @param action 事务内数据库操作
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginTX(Value action) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginTX(action::execute);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @param timeout             设置事务超时时间，-1表示不超时(单位：秒)
     * @param isolationLevel      设置事务隔离级别 {@link org.springframework.transaction.TransactionDefinition#ISOLATION_DEFAULT}
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginReadOnlyTX(Value action, int propagationBehavior, int timeout, int isolationLevel) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginReadOnlyTX(action::execute, propagationBehavior, timeout, isolationLevel);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @param timeout             设置事务超时时间，-1表示不超时(单位：秒)
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginReadOnlyTX(Value action, int propagationBehavior, int timeout) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginReadOnlyTX(action::execute, propagationBehavior, timeout);
    }

    /**
     * 在事务内支持操作
     *
     * @param action              事务内数据库操作
     * @param propagationBehavior 设置事务传递性 {@link org.springframework.transaction.TransactionDefinition#PROPAGATION_REQUIRED}
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginReadOnlyTX(Value action, int propagationBehavior) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginReadOnlyTX(action::execute, propagationBehavior);
    }

    /**
     * 在事务内支持操作
     *
     * @param action 事务内数据库操作
     * @see org.springframework.transaction.TransactionDefinition
     */
    public Value beginReadOnlyTX(Value action) {
        Assert.isTrue(action != null && action.canExecute(), "参数action必须是回调函数");
        return delegate.beginReadOnlyTX(action::execute);
    }

    // --------------------------------------------------------------------------------------------
    //  其它 操作
    // --------------------------------------------------------------------------------------------

    /**
     * 获取数据源信息
     */
    public JdbcInfo getInfo() {
        return delegate.getInfo();
    }

    /**
     * 获取数据源状态
     */
    public JdbcDataSourceStatus getStatus() {
        return delegate.getStatus();
    }

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
        Object orderField = sortMap.get(Order_Field_Name);
        Object sort = sortMap.get(Sort_Name);
        Object orderFields = sortMap.get(Order_Fields_Name);
        Object sorts = sortMap.get(Sorts_Name);
        Object fieldsMapping = sortMap.get(Fields_Mapping_Name);
        Assert.isTrue(orderField == null || orderField instanceof String, "参数orderField必须是一个字符串");
        Assert.isTrue(sort == null || sort instanceof String, "参数sort必须是一个字符串");
        Assert.isTrue(orderFields == null || orderFields instanceof Object[], "参数orderFields必须是一个字符串数组");
        Assert.isTrue(sorts == null || sorts instanceof Object[], "参数sorts必须是一个字符串数组");
        Assert.isTrue(fieldsMapping == null || fieldsMapping instanceof Map, "参数sorts必须是一个字符串数组");
        // 排序参数转换
        if (queryBySort == null) {
            queryBySort = new QueryBySort();
        }
        queryBySort.setOrderField((String) orderField);
        queryBySort.setSort((String) sort);
        if (orderFields != null) {
            String[] orderFieldsArr = new String[((Object[]) orderFields).length];
            for (int i = 0; i < orderFieldsArr.length; i++) {
                Object item = ((Object[]) orderFields)[i];
                if (item == null) {
                    orderFieldsArr[i] = null;
                } else if (item instanceof String) {
                    orderFieldsArr[i] = (String) item;
                } else {
                    throw new IllegalArgumentException("参数orderFields必须是一个字符串数组");
                }
            }
            queryBySort.setOrderFields(Arrays.asList(orderFieldsArr));
        }
        if (sorts != null) {
            String[] sortsArr = new String[((Object[]) sorts).length];
            for (int i = 0; i < sortsArr.length; i++) {
                Object item = ((Object[]) sorts)[i];
                if (item == null) {
                    sortsArr[i] = null;
                } else if (item instanceof String) {
                    sortsArr[i] = (String) item;
                } else {
                    throw new IllegalArgumentException("参数sorts必须是一个字符串数组");
                }
            }
            queryBySort.setSorts(Arrays.asList(sortsArr));
        }
        if (fieldsMapping != null) {
            Map<?, ?> map = (Map<?, ?>) fieldsMapping;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();

                queryBySort.addOrderFieldMapping(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        return queryBySort;
    }
}
