package com.hg.hgblogback.dto.response.posts;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.object.FavoriteListItem;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDTO extends ResponseDTO {

    private List<FavoriteListItem> favoriteList;

    //# 생성자
    public GetFavoriteListResponseDTO(List<GetFavoriteListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteList = FavoriteListItem.copyList(resultSets);
    }
    
    //# success
    public static ResponseEntity<GetFavoriteListResponseDTO> success(List<GetFavoriteListResultSet> resultSets) {
        GetFavoriteListResponseDTO result = new GetFavoriteListResponseDTO(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# nonExistentPosts
    public static ResponseEntity<ResponseDTO> nonExistentPosts() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_POST, ResponseMessage.NON_EXISTENT_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
