package org.clever.hinny.graal.mvc;

import org.apache.commons.lang3.StringUtils;
import org.clever.hinny.api.ScriptEngineInstance;
import org.clever.hinny.api.ScriptObject;
import org.clever.hinny.api.folder.Folder;
import org.clever.hinny.api.pool.EngineInstancePool;
import org.clever.hinny.api.utils.JacksonMapper;
import org.clever.hinny.mvc.HttpRequestScriptHandler;
import org.clever.hinny.mvc.http.HttpContext;
import org.clever.hinny.mvc.support.TupleTow;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/24 21:34 <br/>
 */
public class HttpRequestGraalScriptHandler extends HttpRequestScriptHandler<Context, Value> {
    /**
     * 脚本文件后缀
     */
    private static final String ScriptSuffix = ".js";

    public HttpRequestGraalScriptHandler(String supportPrefix, Set<String> supportSuffix, EngineInstancePool<Context, Value> engineInstancePool) {
        super(supportPrefix, supportSuffix, engineInstancePool);
    }

    public HttpRequestGraalScriptHandler(EngineInstancePool<Context, Value> engineInstancePool) {
        super(engineInstancePool);
    }

    @Override
    protected boolean fileExists(ScriptEngineInstance<Context, Value> engineInstance, String fullPath) {
        if (!fullPath.endsWith(ScriptSuffix)) {
            fullPath = fullPath + ScriptSuffix;
        }
        Folder folder = engineInstance.getRootPath().create(fullPath);
        return folder != null && folder.isFile();
    }

    @Override
    protected TupleTow<Object, Boolean> doHandle(HttpServletRequest request, HttpServletResponse response, TupleTow<ScriptObject<Value>, String> handlerScript) {
        HttpContext httpContext = new HttpContext(request, response);
        final ScriptObject<Value> scriptObject = handlerScript.getValue1();
        final String method = handlerScript.getValue2();
        Object fucObject = scriptObject.getMember(method);
        //  fucObject类型不正确 - 跳过Script处理
        if (!(fucObject instanceof Value)) {
            return TupleTow.creat(null, true);
        }
        Value fucValue = (Value) fucObject;
        // fucValue是一个函数
        if (fucValue.canExecute()) {
            Value res = fucValue.execute(httpContext);
            return TupleTow.creat(res, false);
        }
        // fucValue 是 HttpRouter 对象
        final String httpMethod = StringUtils.lowerCase(httpContext.request.getMethod());
        Value httpRouter = fucValue.getMember(httpMethod);
        if (httpRouter != null && httpRouter.canExecute()) {
            Value res = httpRouter.execute(httpContext);
            return TupleTow.creat(res, false);
        }
        // 尝试 httpHandle
        Value httpHandle = getHttpHandle(httpMethod, fucValue);
        if (httpHandle == null) {
            // httpHandle 不支持 - 跳过Script处理
            return TupleTow.creat(null, true);
        }
        Value res = httpHandle.execute(httpContext);
        return TupleTow.creat(res, false);
    }

    @Override
    protected boolean resIsEmpty(Object res) {
        if (res instanceof Value) {
            return ((Value) res).isNull();
        }
        return res == null;
    }

    @Override
    protected String serializeRes(Object res) {
        return JacksonMapper.getInstance().toJson(res);
    }

    /**
     * 获取 HttpHandle 函数
     */
    protected Value getHttpHandle(String httpMethod, Value fucValue) {
        Set<String> methodSet = getHttpMethod(fucValue);
        // 匹配 HttpMethod
        if (!methodSet.isEmpty() && !methodSet.contains(StringUtils.upperCase(httpMethod))) {
            return null;
        }
        // HttpHandle 是可执行函数
        Value httpHandle = fucValue.getMember("handle");
        if (httpHandle == null || !httpHandle.canExecute()) {
            return null;
        }
        return httpHandle;
    }

    /**
     * 获取 HttpHandle 函数支持的 HttpMethod
     */
    protected Set<String> getHttpMethod(Value fucValue) {
        Value methods = fucValue.getMember("method");
        if (methods == null) {
            return Collections.emptySet();
        }
        if (methods.isString()) {
            Set<String> methodSet = new HashSet<>(1);
            methodSet.add(methods.asString());
            return methodSet;
        }
        if (methods.hasArrayElements()) {
            Set<String> methodSet = new HashSet<>((int) methods.getArraySize());
            for (int i = 0; i < methods.getArraySize(); i++) {
                Value item = methods.getArrayElement(i);
                if (item != null && item.isString()) {
                    methodSet.add(item.asString());
                }
            }
            return methodSet;
        }
        return Collections.emptySet();
    }
}
