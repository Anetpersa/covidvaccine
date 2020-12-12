package com.amazingwomenstory.covidvaccine.utils;

public class Constants {

    /**
     * Swagger
     */
    public static final String SWAGGER = "/swagger-ui.html";

    /**
     * Endpoints
     */
    public static final String API_PREFIX = "/api";

    // SkillManager Info API
    public static final String API_PREFIX_INFO = API_PREFIX + "/info";
    public static final String ENDPOINT_SUFFIX_INFO_GENERAL = "/general";

    // Admin API
    public static final String API_PREFIX_ADMIN = API_PREFIX + "/admin";
    public static final String API_PREFIX_ADMIN_UPLOAD_FILE = "/upload-csv-file";

    // User API
    public static final String API_PREFIX_USER = API_PREFIX + "/user";

     /**
     * Status codes
     */
    public static final String STATUS_CODE_SUCCESS = "SUCCESS";

    public static final String STATUS_CODE_FAILURE = "FAILURE";

    public static final String STATUS_CODE_DEFAULT = STATUS_CODE_SUCCESS;
}
