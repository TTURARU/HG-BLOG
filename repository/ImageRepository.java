package com.hg.hgblogback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hg.hgblogback.entity.ImageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    List<ImageEntity> findByPostsNumber(Integer postsNumber);

    @Transactional
    void deleteByPostsNumber(Integer postsNumber);
}
