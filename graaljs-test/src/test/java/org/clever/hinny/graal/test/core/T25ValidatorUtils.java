package org.clever.hinny.graal.test.core;

import lombok.extern.slf4j.Slf4j;
import org.clever.common.model.ValidMessage;
import org.graalvm.polyglot.PolyglotException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void t02() {
        try {
            scriptObject.callMember("t02");
        } catch (Exception e) {
            if (!(e instanceof PolyglotException)) {
                return;
            }
            Throwable throwable = ((PolyglotException) e).asHostException();
            if (!(throwable instanceof BindException)) {
                return;
            }
            BindingResult bindingResult = ((BindException) throwable).getBindingResult();
            if (!bindingResult.hasErrors()) {
                return;
            }
            List<ValidMessage> validMessageList = new ArrayList<>();
            List<FieldError> validError = bindingResult.getFieldErrors();
            for (FieldError fieldError : validError) {
                validMessageList.add(new ValidMessage(fieldError));
            }
            log.info("validMessageList -> {}", validMessageList);
        }
        log.info("### ---------------------------------------------------------------------------> END");
    }
}
