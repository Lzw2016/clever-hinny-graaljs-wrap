package org.clever.hinny.graal.test.core;

import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.ScriptObject;
import org.clever.hinny.api.folder.FileSystemFolder;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.graaljs.GraalScriptEngineInstance;
import org.graalvm.polyglot.Engine;
import org.junit.After;

import java.io.File;
import java.io.IOException;

@Slf4j
public abstract class AbstractTest {
    // yz笔记本路径
    protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("C:\\Users\\mlrs\\Desktop\\clever-hinny-js").getAbsolutePath());
    // yz公司路径
//    protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("C:\\Users\\ymx\\Desktop\\clever-hinny-js").getAbsolutePath());
    // lzw家里的路径
//    protected final Folder rootFolder = FileSystemFolder.createRootPath(new File("D:\\SourceCode\\clever\\clever-hinny-js").getAbsolutePath());
    protected ScriptEngineInstance<?, ?> engineInstance;
    protected ScriptObject<?> scriptObject;

    public void before() throws Exception {
        // clever-hinny-graaljs
        log.info("### rootFolder -> {}", rootFolder);

        Engine engine = Engine.newBuilder()
                .useSystemProperties(true)
                .build();
        engineInstance = GraalScriptEngineInstance.Builder.create(engine, rootFolder)
                .build();
    }

    @After
    public void after() throws IOException {
        engineInstance.close();
    }
}
