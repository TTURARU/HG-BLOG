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
public class GetTop3PostsListResponseDTO extends ResponseDTO {
        private List<PostsListItem> top3List;

    //# 생성자
    private GetTop3PostsListResponseDTO(List<PostsListViewEntity> postsListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = PostsListItem.getList(postsListViewEntities);
    }

    //# success
    public static ResponseEntity<GetTop3PostsListResponseDTO> success(List<PostsListViewEntity> postsListViewEntities) {
        GetTop3PostsListResponseDTO result = new GetTop3PostsListResponseDTO(postsListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
