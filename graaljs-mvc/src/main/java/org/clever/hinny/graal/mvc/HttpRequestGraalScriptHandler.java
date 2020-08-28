package org.clever.hinny.graal.mvc;

import lombok.extern.slf4j.Slf4j;
import org.clever.hinny.api.pool.EngineInstancePool;
import org.clever.hinny.mvc.HttpRequestScriptHandler;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.Set;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/24 21:34 <br/>
 */
@Slf4j
public class HttpRequestGraalScriptHandler extends HttpRequestScriptHandler<Context, Value> {

    public HttpRequestGraalScriptHandler(String supportPrefix, Set<String> supportSuffix, EngineInstancePool<Context, Value> engineInstancePool) {
        super(supportPrefix, supportSuffix, engineInstancePool);
    }

    public HttpRequestGraalScriptHandler(EngineInstancePool<Context, Value> engineInstancePool) {
        super(engineInstancePool);
    }

    @Override
    protected boolean fileExists(String fullPath) {
        return false;
    }
}
