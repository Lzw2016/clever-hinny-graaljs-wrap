package org.clever.hinny.graal.test.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class T02InteropTest extends AbstractJdbcDataSourceTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/base/02Interop");
    }

    @Test
    public void t01() {
        scriptObject.callMember("t01");
        log.info("--------------------------------------------------------------------------------------------");
    }

    @Test
    public void t02() {
        scriptObject.callMember("t02");
        log.info("--------------------------------------------------------------------------------------------");
    }

    @Test
    public void t03() {
        scriptObject.callMember("t03");
        log.info("--------------------------------------------------------------------------------------------");
    }

    @Test
    public void t04() {
        scriptObject.callMember("t04");
        log.info("--------------------------------------------------------------------------------------------");
    }

    @Test
    public void t05() {
        scriptObject.callMember("t05");
        log.info("--------------------------------------------------------------------------------------------");
    }
}
