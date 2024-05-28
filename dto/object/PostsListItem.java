package com.hg.hgblogback.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.hg.hgblogback.entity.PostsListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsListItem {
    private int postsNumber;
    private String title;
    private String content;
    private String postsTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;

    //# 생성자
    public PostsListItem(PostsListViewEntity postsListViewEntity) {
        this.postsNumber = postsListViewEntity.getPostsNumber();
        this.title = postsListViewEntity.getTitle();
        this.content = postsListViewEntity.getContent();
        this.postsTitleImage = postsListViewEntity.getTitleImage();
        this.favoriteCount = postsListViewEntity.getFavoriteCount();
        this.commentCount = postsListViewEntity.getCommentCount();
        this.viewCount = postsListViewEntity.getViewCount();
        this.writeDatetime = postsListViewEntity.getWriteDatetime();
        this.writerNickname = postsListViewEntity.getWriterNickname();
        this.writerProfileImage = postsListViewEntity.getWriterProfileImage();
    }
    //# 객체 변환 후 리스트로 반환
    public static List<PostsListItem> getList(List<PostsListViewEntity> postsListViewEntities) {
        List<PostsListItem> list = new ArrayList<>();
        for(PostsListViewEntity postsListViewEntity: postsListViewEntities) {
            PostsListItem postsListItem = new PostsListItem(postsListViewEntity);
            list.add(postsListItem);
        }
        return list;
    }
} //* PostsListItem */
