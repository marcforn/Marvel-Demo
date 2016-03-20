package com.mforn.data.backend;


import com.mforn.data.BuildConfig;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Constants for Backend connections.
 */
public class BackendConstants {

    // Credentials
    public static final String PUBLIC_KEY = "73d133088ff096a7d4e0ef67d665836f";
    public static final String PRIVATE_KEY = "d39e683f84acf06b657cfe80643a257c7d578a65";

    //Parameters
    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_TS = "ts";
    public static final String PARAM_API_KEY = "apikey";
    public static final String PARAM_HASH = "hash";
    public static final String PARAM_CHARACTER_ID = "characterId";

    // Paths
    public static final String PATH_ROOT = BuildConfig.PATH_ROOT;
    public static final String PATH_GET_CHARACTER_LIST = "characters";
    public static final String PATH_GET_CHARACTER_DETAIL = "characters/{" + PARAM_CHARACTER_ID + "}";

    // Connection Configuration
    public static final HttpLoggingInterceptor.Level LOG_LEVEL = BuildConfig.LOG_LEVEL;
    public static final int TIME_OUT_MILLIS = 15000;

    // Data Configuration
    public static final int LIMIT_CHARACTERS_RETRIEVED = 40;
}
