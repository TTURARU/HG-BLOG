package com.hg.hgblogback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hg.hgblogback.dto.response.search.GetPopularListResponseDTO;
import com.hg.hgblogback.dto.response.search.GetRelationListResponseDTO;
import com.hg.hgblogback.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    //# 인기 검색어 리스트
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
        ResponseEntity<? super GetPopularListResponseDTO> response = searchService.getPopularList();
        return response;
    } //* getPopularList */

    //# 연관 검색어 리스트
    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(
        @PathVariable("searchWord") String searchWord
    ){
        ResponseEntity<? super GetRelationListResponseDTO> response = searchService.getRelationList(searchWord);
        return response;
    } //* getRelationList */

}
