package com.hg.hgblogback.service;

import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.dto.response.search.GetPopularListResponseDTO;
import com.hg.hgblogback.dto.response.search.GetRelationListResponseDTO;

public interface SearchService {
    //# 인기 검색어 리스트
    ResponseEntity<? super GetPopularListResponseDTO> getPopularList();
    //# 연관 검색어 리스트
    ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord);
}
