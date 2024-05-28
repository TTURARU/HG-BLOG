package com.hg.hgblogback.dto.response.posts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class CreatePostsResponseDTO extends ResponseDTO {
    //# 생성자
    private CreatePostsResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# success
    public static ResponseEntity<CreatePostsResponseDTO> success() {
        CreatePostsResponseDTO result = new CreatePostsResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
