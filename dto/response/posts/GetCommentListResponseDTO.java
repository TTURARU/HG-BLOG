package com.hg.hgblogback.dto.response.posts;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.object.CommentListItem;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDTO extends ResponseDTO {
    private List<CommentListItem> commentList;

    //# 생성자
    private GetCommentListResponseDTO(List<GetCommentListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.copyList(resultSets);
    }

    //# success
    public static ResponseEntity<GetCommentListResponseDTO> success(List<GetCommentListResultSet> resultSets){
        GetCommentListResponseDTO result = new GetCommentListResponseDTO(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# nonExistentPosts
    public static ResponseEntity<ResponseDTO> nonExistentPosts(){
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_POST, ResponseMessage.NON_EXISTENT_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
