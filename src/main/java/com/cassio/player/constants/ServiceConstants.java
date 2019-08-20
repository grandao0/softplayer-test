package com.cassio.player.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceConstants {

    public static final String BASE_PACKAGE = "com.cassio.player";
    public static final String CONTROLLERS_BASE_PACKAGE = "com.cassio.player.controllers";
    public static final String GIT_URL = "https://github.com/grandao0";
    public static final String SWAGGER_PATH = "/";
    public static final String VERSION_NUMBER = "1.0";

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
