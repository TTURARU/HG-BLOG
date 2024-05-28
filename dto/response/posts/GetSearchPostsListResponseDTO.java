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
public class GetSearchPostsListResponseDTO extends ResponseDTO {
    private List<PostsListItem> searchList;

    //# 생성자
    private GetSearchPostsListResponseDTO(List<PostsListViewEntity> postsListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = PostsListItem.getList(postsListViewEntities);
    }

    //# success
    public static ResponseEntity<GetSearchPostsListResponseDTO> success(List<PostsListViewEntity> postsListViewEntities){
        GetSearchPostsListResponseDTO result = new GetSearchPostsListResponseDTO(postsListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
