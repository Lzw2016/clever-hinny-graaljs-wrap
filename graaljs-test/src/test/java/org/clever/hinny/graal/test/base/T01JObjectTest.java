package org.clever.hinny.graal.test.base;

import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.ScriptObject;
import org.clever.hinny.api.folder.FileSystemFolder;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.graaljs.GraalScriptEngineInstance;
import org.graalvm.polyglot.Engine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/07/31 21:52 <br/>
 */
@Slf4j
public class T01JObjectTest {
    private final Folder rootFolder = FileSystemFolder.createRootPath(new File("D:\\SourceCode\\clever\\clever-hinny-js").getAbsolutePath());

    private ScriptEngineInstance<?, ?> engineInstance;
    private ScriptObject<?> scriptObject;

    @Before
    public void before1() throws Exception {
        log.info("### rootFolder -> {}", rootFolder);
        Engine engine = Engine.newBuilder()
                .useSystemProperties(true)
                .build();
        engineInstance = GraalScriptEngineInstance.Builder.create(engine, rootFolder).build();
        scriptObject = engineInstance.require("/test/dist/base/01jobject");
    }

    @After
    public void after() throws IOException {
        engineInstance.close();
        log.info("### ---> END");
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
}
