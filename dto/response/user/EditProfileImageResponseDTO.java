package com.hg.hgblogback.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class EditProfileImageResponseDTO extends ResponseDTO {
    //# 생성자
    private EditProfileImageResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# success
    public static ResponseEntity<EditProfileImageResponseDTO> success() {
        EditProfileImageResponseDTO result = new EditProfileImageResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
