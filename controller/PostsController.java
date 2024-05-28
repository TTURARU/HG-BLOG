package com.hg.hgblogback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.hg.hgblogback.service.PostsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;

    //# 게시물 작성
    @PostMapping("")
    public ResponseEntity<? super CreatePostsResponseDTO> createPosts(
        @RequestBody @Valid CreatePostsRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super CreatePostsResponseDTO> response = postsService.createPosts(requestBody, email);
        return response;
    } //* createPosts */

    //# 게시물 상세
    @GetMapping("/{postsNumber}")
    public ResponseEntity<? super DetailPostsResponseDTO> detailPosts(
        @PathVariable("postsNumber") Integer postsNumber
    ){
        ResponseEntity<? super DetailPostsResponseDTO> response = postsService.detailPosts(postsNumber);
        return response;
    }//* detailPosts */

    //# 게시물 수정
    @PatchMapping("/{postsNumber}")
    public ResponseEntity<? super EditPostsResponseDTO> editPosts(
        @RequestBody @Valid EditPostsRequestDTO requestBody,
        @PathVariable("postsNumber") Integer postsNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super EditPostsResponseDTO> response = postsService.editPosts(requestBody, postsNumber, email);
        return response;
    } //* editPosts */

    //# 게시물 삭제
    @DeleteMapping("/{postsNumber}")
    public ResponseEntity<? super DeletePostsResponseDTO> deletePosts(
        @PathVariable("postsNumber") Integer postsNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super DeletePostsResponseDTO> response = postsService.deletePosts(postsNumber, email);
        return response;
    } //* deletePosts */


    //# 좋아요 누르기
    @PutMapping("/{postsNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(
        @PathVariable("postsNumber") Integer postsNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PutFavoriteResponseDTO> response = postsService.putFavorite(postsNumber, email);
        return response;
    } //* putFavorite */

    //# 좋아요 리스트
    @GetMapping("/{postsNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(
        @PathVariable("postsNumber") Integer postsNumber
    ){
        ResponseEntity<? super GetFavoriteListResponseDTO> response = postsService.getFavoriteList(postsNumber);
        return response;
    } //* getFavoriteList */

    //# 댓글 작성
    @PostMapping("/{postsNumber}/comment")
    public ResponseEntity<? super CreateCommentResponseDTO> createComment(
        @RequestBody @Valid CreateCommentRequestDTO requestBody,
        @PathVariable("postsNumber") Integer postsNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super CreateCommentResponseDTO> response = postsService.createComment(requestBody, postsNumber, email);
        return response;
    } //* createComment */

    //# 댓글 리스트
    @GetMapping("{postsNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(
        @PathVariable("postsNumber") Integer postsNumber
    ){
        ResponseEntity<? super GetCommentListResponseDTO> response = postsService.getCommentList(postsNumber);
        return response;
    } //* getCommentList */
    
    //# 댓글 삭제
    @DeleteMapping("/{commentNumber}/comment")
    public ResponseEntity<? super DeleteCommentResponseDTO> deleteComment(
        @PathVariable("commentNumber") Integer commentNumber,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super DeleteCommentResponseDTO> response = postsService.deleteComment(commentNumber, email);
        return response;
    }

    //# 조회수 증가
    @GetMapping("/{postsNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDTO> increaseViewCount(
        @PathVariable("postsNumber") Integer postsNumber
    ){
        ResponseEntity<? super IncreaseViewCountResponseDTO> response = postsService.increaseViewCount(postsNumber);
        return response;
    } //* increaseViewCount */
    
    //# 최신 게시물 리스트
    @GetMapping("/latest-list")
    public ResponseEntity<? super GetLatestPostsListResponseDTO> getLatestPostsList() {
        ResponseEntity<? super GetLatestPostsListResponseDTO> response = postsService.getLatestPostsList();
        return response;
    } //* getLatestPostsList */
    
    //# 탑3 게시물 리스트
    @GetMapping("/top-3")
    public ResponseEntity<? super GetTop3PostsListResponseDTO> getTop3PostsList() {
        ResponseEntity<? super GetTop3PostsListResponseDTO> response = postsService.getTop3PostsList();
        return response;
    } //* getTop3PostsList */
    
    //# 검색 게시물 리스트
    @GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
    public ResponseEntity<? super GetSearchPostsListResponseDTO> getSearchPostsList(
        @PathVariable("searchWord") String searchWord,
        @PathVariable(value = "preSearchWord", required = false) String preSearchWord
    ){
        ResponseEntity<? super GetSearchPostsListResponseDTO> response = postsService.getSearchPostsList(searchWord, preSearchWord);
        return response;
    } //* getSearchPostsList */

    //# 유저 게시물 리스트
    @GetMapping("/user-posts-list/{email}")
    public ResponseEntity<? super GetUserPostsListResponseDTO> getUserPostsList(
        @PathVariable("email") String email
    ){
        ResponseEntity<? super GetUserPostsListResponseDTO> response = postsService.getUserPostsList(email);
        return response;
    }

} //* PostsController */
