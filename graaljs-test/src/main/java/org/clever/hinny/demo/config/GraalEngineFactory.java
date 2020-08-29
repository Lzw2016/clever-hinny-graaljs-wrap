package org.clever.hinny.demo.config;

import org.clever.hinny.api.AbstractBuilder;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.graaljs.GraalScriptEngineInstance;
import org.clever.hinny.graaljs.pool.GraalSingleEngineFactory;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Value;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/28 20:54 <br/>
 */
public class GraalEngineFactory extends GraalSingleEngineFactory {
    public GraalEngineFactory(Folder rootFolder, Engine engine) {
        super(rootFolder, engine);
    }

    /**
     * 返回ScriptEngineInstance Builder对象
     */
    protected AbstractBuilder<Context, Value, ScriptEngineInstance<Context, Value>> getScriptEngineInstanceBuilder() {
        return GraalScriptEngineInstance.Builder.create(engine, rootFolder)
                .setEngine(getContextBuilder().build());
    }
}
