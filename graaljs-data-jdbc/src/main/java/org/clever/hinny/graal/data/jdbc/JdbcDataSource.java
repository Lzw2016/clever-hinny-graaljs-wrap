package org.clever.hinny.graal.data.jdbc;

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
}
