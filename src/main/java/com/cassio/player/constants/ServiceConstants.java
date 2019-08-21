package com.cassio.player.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceConstants {

    public static final String BASIC_AUTH_USER = "admin";
    public static final String BASIC_AUTH_PASS_BCRYPT = "$2a$04$pyaHPPfw96YyRk6v9FDHmew2lRkTeC9wA5NOL5a8eMvq7r0aOkgrK";
    public static final String BASIC_AUTH_USER_ROLE = "USER";
    public static final String BASE_PACKAGE = "com.cassio.player";
    public static final String CONTROLLERS_BASE_PACKAGE = "com.cassio.player.controllers";
    public static final String GIT_URL = "https://github.com/grandao0/softplayer-test";
    public static final String NO_AUTH_URL = "/api/softplan/v1/source";
    public static final String REALM_NAME = "SoftPlayer";
    public static final String SWAGGER_PATH = "/";
    public static final String VERSION_NUMBER = "1.0";

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
