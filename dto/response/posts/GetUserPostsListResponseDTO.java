package com.hg.hgblogback.dto.response.posts;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.object.PostsListItem;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.entity.PostsListViewEntity;

import lombok.Getter;

@Getter
public class GetUserPostsListResponseDTO extends ResponseDTO {
    private List<PostsListItem> userPostsList;

    //# 생성자
    private GetUserPostsListResponseDTO(List<PostsListViewEntity> postsListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userPostsList = PostsListItem.getList(postsListViewEntities);
    } 

    //# success
    public static ResponseEntity<GetUserPostsListResponseDTO> success(List<PostsListViewEntity> postsListViewEntities){
        GetUserPostsListResponseDTO result = new GetUserPostsListResponseDTO(postsListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //# nonExistentUser
    public static ResponseEntity<ResponseDTO> nonExistentUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_USER, ResponseMessage.NON_EXISTENT_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
