package com.hg.hgblogback.common;

public interface ResponseCode {
    // 200
    String SUCCESS = "SU";
    
    // 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TELNUMBER = "DT";
    String NON_EXISTENT_USER = "NEU";
    String NON_EXISTENT_POST = "NEP";
    String NON_EXISTENT_COMMENT = "NEC";

    // 401
    String LOGIN_FAILED = "LF";
    String AUTHENTICATION_FAILED = "AF";

    // 403
    String NO_PERMISSION = "NP";

    // 500
    String DATABASE_ERROR = "DBE";
} //* ResponseCode */
