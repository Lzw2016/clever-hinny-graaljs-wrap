package org.clever.hinny.graal.test.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/09/05 21:38 <br/>
 */
@Slf4j
public class T25ValidatorUtils extends AbstractTest {
    @Before
    public void before() throws Exception {
        super.before();
        scriptObject = engineInstance.require("/test/dist/core/25ValidatorUtils");
    }

    @Test
    public void t01() {
        scriptObject.callMember("t01");
        log.info("### ---------------------------------------------------------------------------> END");
    }
}