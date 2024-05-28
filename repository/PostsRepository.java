package com.hg.hgblogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hg.hgblogback.entity.PostsEntity;
import com.hg.hgblogback.repository.resultSet.GetPostsResultSet;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Integer>{
    boolean existsByPostsNumber(Integer postsNumber);
    PostsEntity findByPostsNumber(Integer postsNumber);

    @Query(
        value = 
        "SELECT " + 
        "P.posts_number AS postsNumber, " +
        "P.title AS title, " +
        "P.content AS content, " +
        "P.write_datetime AS writeDatetime, " +
        "P.writer_email AS writerEmail, " +
        "U.nickname AS writerNickname, " +
        "U.profile_image AS writerProfileImage, " +
        "P.view_count AS viewCount " +
        "FROM posts as P " +
        "INNER JOIN user as U " + 
        "ON P.writer_email = U.email " +
        "WHERE posts_number = ?1 ",
        nativeQuery = true
    )
    GetPostsResultSet getPosts(Integer postsNumber);
}
