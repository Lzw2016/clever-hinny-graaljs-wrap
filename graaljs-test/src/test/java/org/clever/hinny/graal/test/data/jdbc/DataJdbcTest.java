package org.clever.hinny.graal.test.data.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/30 08:41 <br/>
 */
@Slf4j
public class DataJdbcTest extends AbstractJdbcDataSourceTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/data-jdbc/data-jdbc-test");
    }

    @Test
    public void t01() {
        scriptObject.callMember("t01");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t02() {
        scriptObject.callMember("t02");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t03() {
        scriptObject.callMember("t03");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t04() {
        scriptObject.callMember("t04");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t05() {
        scriptObject.callMember("t05");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t06() {
        scriptObject.callMember("t06");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t07() {
        scriptObject.callMember("t07");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t08() {
        scriptObject.callMember("t08");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t09() {
        scriptObject.callMember("t09");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t10() {
        scriptObject.callMember("t10");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t11() {
        scriptObject.callMember("t11");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t12() {
        scriptObject.callMember("t12");
        log.info("### ---------------------------------------------------------------------------> END");
    }

    @Test
    public void t13() {
        scriptObject.callMember("t13");
        log.info("### ---------------------------------------------------------------------------> END");
    }
}
