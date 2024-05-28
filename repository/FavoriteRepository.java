package com.hg.hgblogback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hg.hgblogback.entity.FavoriteEntity;
import com.hg.hgblogback.entity.primaryKey.FavoritePK;
import com.hg.hgblogback.repository.resultSet.GetFavoriteListResultSet;

import jakarta.transaction.Transactional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePK> {
    FavoriteEntity findByPostsNumberAndUserEmail(Integer postsNumber, String userEmail);

    @Query(
        value = 
        "SELECT " +
        "    U.email AS email, " +
        "    U.nickname AS nickname, " +
        "    U.profile_image AS profileImage " +
        "FROM favorite AS F " + 
        "INNER JOIN user AS U " + 
        "ON F.user_email = U.email " +
        "WHERE F.posts_number = ?1",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer postsNumber);

    @Transactional
    void deleteByPostsNumber(Integer postsNumber);
}
