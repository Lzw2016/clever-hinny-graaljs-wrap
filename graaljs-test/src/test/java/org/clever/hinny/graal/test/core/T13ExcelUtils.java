package org.clever.hinny.graal.test.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/21 23:30 <br/>
 */
@Slf4j
public class T13ExcelUtils extends AbstractTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/core/13ExcelUtils");
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

//    @Test
//    public void t06() {
//        scriptObject.callMember("t06");
//        log.info("### ---------------------------------------------------------------------------> END");
//    }
}