package org.clever.hinny.graal.meta.data;

import org.clever.hinny.meta.data.model.DataBaseSummary;
import org.clever.hinny.meta.data.model.TableSchema;

import javax.sql.DataSource;

/**
 * 作者：lizw <br/>
 * 创建时间：2020-10-01 21:07 <br/>
 */
public class MateDataService {
    private final org.clever.hinny.meta.data.MateDataService delegate;

    public MateDataService(DataSource dataSource) {
        delegate = new org.clever.hinny.meta.data.MateDataService(dataSource);
    }

    /**
     * 重新加载数据库元数据
     */
    public void reload() {
        delegate.reload();
    }

    /**
     * @param database 数据库名称(schema名称)
     */
    public DataBaseSummary getDataBaseSummary(String database) {
        return delegate.getDataBaseSummary(database);
    }

    /**
     * @param database  数据库名称(schema名称)
     * @param tableName 表名称
     */
    public TableSchema getTableSchema(String database, String tableName) {
        return delegate.getTableSchema(database, tableName);
    }
}
