package com.hg.hgblogback.dto.response.posts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;

import lombok.Getter;

@Getter
public class DeleteCommentResponseDTO extends ResponseDTO {
    //# 생성자
    private DeleteCommentResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    //# success
    public static ResponseEntity<DeleteCommentResponseDTO> success() {
        DeleteCommentResponseDTO result = new DeleteCommentResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentComment
    public static ResponseEntity<ResponseDTO> nonExistentComment() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_COMMENT, ResponseMessage.NON_EXISTENT_COMMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    //# noPermission
    public static ResponseEntity<ResponseDTO> noPermission() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
