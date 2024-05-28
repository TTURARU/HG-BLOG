package com.hg.hgblogback.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDTO extends ResponseDTO {
    private String email;
    private String nickname;
    private String profileImage;

    //# 생성자
    public GetUserResponseDTO(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    //# success
    public static ResponseEntity<GetUserResponseDTO> success(UserEntity userEntity) {
        GetUserResponseDTO result = new GetUserResponseDTO(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser(){
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
