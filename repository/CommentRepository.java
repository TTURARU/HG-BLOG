package com.hg.hgblogback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hg.hgblogback.entity.CommentEntity;
import com.hg.hgblogback.repository.resultSet.GetCommentListResultSet;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    CommentEntity findByCommentNumber(Integer commentNumber);

    @Query(
        value =
        "SELECT " + 
        "    U.nickname AS nickname, " +
        "    U.profile_image AS profileImage, " +
        "    C.write_datetime AS writeDatetime, " +
        "    C.content AS content, " +
        "    C.comment_number AS commentNumber " +
        "FROM comment AS C " +
        "INNER JOIN user AS U " +
        "ON C.user_email = U.email " +
        "WHERE C.posts_number = ?1 " +
        "ORDER BY writeDatetime DESC",
        nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer postsNumber);

    @Transactional
    void deleteByPostsNumber(Integer postsNumber);

    @Transactional
    void deleteByCommentNumber(Integer commentNumber);
}
