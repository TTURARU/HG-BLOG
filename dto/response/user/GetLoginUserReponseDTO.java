package com.hg.hgblogback.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetLoginUserReponseDTO extends ResponseDTO {
    private String email;
    private String nickname;
    private String profileImage;

    //# 로그인 유저 초기화 생성자
    private GetLoginUserReponseDTO(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    //# success
    public static ResponseEntity<GetLoginUserReponseDTO> success(UserEntity userEntity) {
        GetLoginUserReponseDTO result = new GetLoginUserReponseDTO(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
