package org.clever.hinny.graal.test.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/15 22:15 <br/>
 */
@Slf4j
public class T01HttpUtils extends AbstractTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/core/01HttpUtils");
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
}
