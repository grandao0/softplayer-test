package com.cassio.player.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.METHOD})
public @interface OauthSecuredApi {
    String[] allowedScopes() default {};
}
