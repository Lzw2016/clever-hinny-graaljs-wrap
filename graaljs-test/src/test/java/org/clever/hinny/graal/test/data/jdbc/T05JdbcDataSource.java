package org.clever.hinny.graal.test.data.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/04 21:04 <br/>
 */
@Slf4j
public class T05JdbcDataSource extends AbstractJdbcDataSourceTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/data-jdbc/05JdbcDataSource-insert");
    }

    @Test
    public void t01() {
        scriptObject.callMember("t01");
        log.info("### ---------------------------------------------------------------------------> END");
    }
}
