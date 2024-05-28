package com.hg.hgblogback.dto.response.posts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.entity.ImageEntity;
import com.hg.hgblogback.repository.resultSet.GetPostsResultSet;

import lombok.Getter;

@Getter
public class DetailPostsResponseDTO extends ResponseDTO {
    private int postsNumber;
    private String title;
    private String content;
    private List<String> postsImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;
    private int viewCount;

    //# 생성자
    public DetailPostsResponseDTO(GetPostsResultSet resultSet, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> postsImageList = new ArrayList<>();
        for(ImageEntity imageEntity: imageEntities) {
            String postsImage = imageEntity.getImage();
            postsImageList.add(postsImage);
        }

        this.postsNumber = resultSet.getPostsNumber();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.postsImageList = postsImageList;
        this.writeDatetime = resultSet.getWriteDatetime();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();
        this.viewCount = resultSet.getViewCount();
    } //* 생성자 */
    
    //# success
    public static ResponseEntity<DetailPostsResponseDTO> success(GetPostsResultSet resultSet, List<ImageEntity> imageEntities) {
        DetailPostsResponseDTO result = new DetailPostsResponseDTO(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //# nonExistentPosts
    public static ResponseEntity<ResponseDTO> nonExistentPosts() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NON_EXISTENT_POST, ResponseMessage.NON_EXISTENT_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
