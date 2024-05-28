package com.hg.hgblogback.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.hg.hgblogback.dto.request.posts.CreatePostsRequestDTO;
import com.hg.hgblogback.dto.request.posts.EditPostsRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="posts")
@Table(name="posts")
public class PostsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postsNumber;
    private String title;
    private String content;
    private String writeDatetime;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writerEmail;

    //# 게시물 작성 요청 처리
    public PostsEntity(CreatePostsRequestDTO dto, String email) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
        this.writerEmail = email;
    }

    //# 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }
    //# 좋아요 증가
    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }
    //# 좋아요 감소
    public void decreaseFavoriteCount() {
        this.favoriteCount--;
    }
    //# 댓글 증가
    public void increaseCommentCount() {
        this.commentCount++;
    }
    //# 게시물 수정
    public void editPosts(EditPostsRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
    

} //* PostsEntity */
