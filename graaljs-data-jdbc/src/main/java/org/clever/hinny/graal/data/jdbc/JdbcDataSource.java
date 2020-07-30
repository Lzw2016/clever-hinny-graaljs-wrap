package org.clever.hinny.graal.data.jdbc;

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

    public void close() throws Exception {
        delegate.close();
    }

    // --------------------------------------------------------------------------------------------
    // Query 操作
    // --------------------------------------------------------------------------------------------

    /**
     * 查询多条数据，返回一个Map数组
     *
     * @param sql sql脚本，参数格式[:param]
     */
    public List<Map<String, Object>> queryList(String sql) {
        return delegate.queryList(sql);
    }
}
