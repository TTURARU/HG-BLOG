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
public class GetLatestPostsListResponseDTO extends ResponseDTO {
    private List<PostsListItem> latestList;

    //# 생성자
    private GetLatestPostsListResponseDTO(List<PostsListViewEntity> postsEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = PostsListItem.getList(postsEntities);
    }

    //# success
    public static ResponseEntity<GetLatestPostsListResponseDTO> success(List<PostsListViewEntity> postsEntities) {
        GetLatestPostsListResponseDTO result = new GetLatestPostsListResponseDTO(postsEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
