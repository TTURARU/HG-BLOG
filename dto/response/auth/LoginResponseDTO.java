package com.hg.hgblogback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class LoginResponseDTO extends ResponseDTO {

    private String token;
    private int expirationTime;

    private LoginResponseDTO(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600;
    }

    //# success
    public static ResponseEntity<LoginResponseDTO> success(String token) {
        LoginResponseDTO result = new LoginResponseDTO(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# loginFailed
    public static ResponseEntity<ResponseDTO> loginFailed() {
        ResponseDTO result = new ResponseDTO(ResponseCode.LOGIN_FAILED, ResponseMessage.LOGIN_FAILED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
