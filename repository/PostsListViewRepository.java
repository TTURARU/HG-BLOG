package com.hg.hgblogback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hg.hgblogback.entity.PostsListViewEntity;

@Repository
public interface PostsListViewRepository extends JpaRepository<PostsListViewEntity, Integer>{
    //# 최신 게시물 리스트 불러오기
    List<PostsListViewEntity> findByOrderByWriteDatetimeDesc();
    //# TOP3 게시물 리스트 불러오기
    List<PostsListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
    //# 검색 게시물 리스트 불러오기
    List<PostsListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);
    //# 작성자 게시물 리스트 불러오기
    List<PostsListViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String writerEmail);
}
