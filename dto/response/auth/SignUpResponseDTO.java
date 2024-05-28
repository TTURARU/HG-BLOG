package com.hg.hgblogback.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class SignUpResponseDTO extends ResponseDTO{
    public SignUpResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# 회원가입 성공
    public static ResponseEntity<SignUpResponseDTO> success() {
        SignUpResponseDTO result = new SignUpResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# 이메일 중복 오류
    public static ResponseEntity<ResponseDTO> duplicateEmail() {
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    //# 닉네임 중복 오류
    public static ResponseEntity<ResponseDTO> duplicateNickname() {
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    //# 전화번호 중복 오류
    public static ResponseEntity<ResponseDTO> duplicateTelNumber() {
        ResponseDTO result = new ResponseDTO(ResponseCode.DUPLICATE_TELNUMBER, ResponseMessage.DUPLICATE_TELNUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
