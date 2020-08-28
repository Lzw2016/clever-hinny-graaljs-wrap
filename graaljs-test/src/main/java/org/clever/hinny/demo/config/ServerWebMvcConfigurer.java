package org.clever.hinny.demo.config;

import org.clever.hinny.graal.mvc.HttpRequestGraalScriptHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作者： lzw<br/>
 * 创建时间：2019-05-17 14:29 <br/>
 */
@Configuration
public class ServerWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private HttpRequestGraalScriptHandler httpRequestGraalScriptHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpRequestGraalScriptHandler).addPathPatterns("/**").order(Integer.MAX_VALUE);
    }
}
