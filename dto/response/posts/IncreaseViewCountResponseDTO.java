package com.hg.hgblogback.dto.response.posts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

public class IncreaseViewCountResponseDTO extends ResponseDTO {
    //# 생성자
    private IncreaseViewCountResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# success
    public static ResponseEntity<IncreaseViewCountResponseDTO> success() {
        IncreaseViewCountResponseDTO result = new IncreaseViewCountResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentPosts
    public static ResponseEntity<ResponseDTO> nonExistentPosts() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_POST, ResponseMessage.NON_EXISTENT_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
