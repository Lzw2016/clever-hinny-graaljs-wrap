package org.clever.hinny.graal.test;

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

    @Before
    public void before1() {
        log.info("### rootFolder -> {}", rootFolder);
        Engine engine = Engine.newBuilder()
                .useSystemProperties(true)
                .build();
        engineInstance = GraalScriptEngineInstance.Builder.create(engine, rootFolder).build();
    }

    @After
    public void after() throws IOException {
        engineInstance.close();
    }

    @Test
    public void t01() throws Exception {
        ScriptObject<?> scriptObject = engineInstance.require("/test/dist/01jobject");
        scriptObject.callMember("t01");
        log.info("--------------------------------------------------------------------------------------------");
        scriptObject.callMember("t02");
        log.info("--------------------------------------------------------------------------------------------");
        log.info("### ---> END");
    }
}
