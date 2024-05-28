package com.hg.hgblogback.service;

import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.dto.request.posts.CreateCommentRequestDTO;
import com.hg.hgblogback.dto.request.posts.CreatePostsRequestDTO;
import com.hg.hgblogback.dto.request.posts.EditPostsRequestDTO;
import com.hg.hgblogback.dto.response.posts.CreateCommentResponseDTO;
import com.hg.hgblogback.dto.response.posts.CreatePostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.DeleteCommentResponseDTO;
import com.hg.hgblogback.dto.response.posts.DeletePostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.DetailPostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.EditPostsResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetCommentListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetFavoriteListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetLatestPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetSearchPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetTop3PostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.GetUserPostsListResponseDTO;
import com.hg.hgblogback.dto.response.posts.IncreaseViewCountResponseDTO;
import com.hg.hgblogback.dto.response.posts.PutFavoriteResponseDTO;

public interface PostsService {
    //# 게시물 작성
    ResponseEntity<? super CreatePostsResponseDTO> createPosts(CreatePostsRequestDTO dto, String email);
    //# 게시물 상세
    ResponseEntity<? super DetailPostsResponseDTO> detailPosts(Integer postsNumber);
    //# 게시물 수정
    ResponseEntity<? super EditPostsResponseDTO> editPosts(EditPostsRequestDTO dto, Integer postsNumber, String email);
    //# 게시물 삭제
    ResponseEntity<? super DeletePostsResponseDTO> deletePosts(Integer postsNumber, String email);
    //# 좋아요 누르기
    ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer postsNumber, String email);
    //# 좋아요 리스트
    ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer postsNumber);
    //# 댓글 작성
    ResponseEntity<? super CreateCommentResponseDTO> createComment(CreateCommentRequestDTO dto, Integer postsNumber, String email);
    //# 댓글 리스트
    ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer postsNumber);
    //# 댓글 삭제
    ResponseEntity<? super DeleteCommentResponseDTO> deleteComment(Integer commentNumber, String email);
    //# 조회수 증가
    ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(Integer postsNumber);
    //# 최신 게시물 리스트
    ResponseEntity<? super GetLatestPostsListResponseDTO> getLatestPostsList();
    //# 탑3 게시물 리스트
    ResponseEntity<? super GetTop3PostsListResponseDTO> getTop3PostsList();
    //# 검색 게시물 리스트
    ResponseEntity<? super GetSearchPostsListResponseDTO> getSearchPostsList(String searchWord, String preSearchWord);
    //# 유저 게시물 리스트
    ResponseEntity<? super GetUserPostsListResponseDTO> getUserPostsList(String email);
}
