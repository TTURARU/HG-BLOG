package com.hg.hgblogback.dto.response.posts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class CreateCommentResponseDTO extends ResponseDTO {
    //# 생성자
    private CreateCommentResponseDTO(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# success
    public static ResponseEntity<CreateCommentResponseDTO> success() {
        CreateCommentResponseDTO result = new CreateCommentResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentPosts
    public static ResponseEntity<ResponseDTO> nonExistentPosts() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_POST, ResponseMessage.NON_EXISTENT_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
