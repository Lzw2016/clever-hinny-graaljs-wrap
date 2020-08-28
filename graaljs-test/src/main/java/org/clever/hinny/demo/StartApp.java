package org.clever.hinny.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.TimeZone;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/28 20:28 <br/>
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.clever"})
public class StartApp {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        ApplicationContext ctx = SpringApplication.run(StartApp.class, args);
        log.info("### 服务启动完成 === " + ctx);
    }
}
