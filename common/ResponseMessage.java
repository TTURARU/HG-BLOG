package com.hg.hgblogback.common;

public interface ResponseMessage {
    // 200
    String SUCCESS = "Success"; 

    // 400
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate nickname";
    String DUPLICATE_TELNUMBER = "Duplicate telnumber";
    String NON_EXISTENT_USER = "This user does not exist";
    String NON_EXISTENT_POST = "This post does not exist";
    String NON_EXISTENT_COMMENT = "This comment does not exist";

    // 401
    String LOGIN_FAILED = "Login information does not match";
    String AUTHENTICATION_FAILED = "Authentication failed";
    
    // 403
    String NO_PERMISSION = "You do not have permission";

    // 500
    String DATABASE_ERROR = "Database error";
} //* ResponseMessage */
