package com.cassio.player.utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Inject
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    public String get(String code, Object... args) {
        return accessor.getMessage(code, args);
    }
}
