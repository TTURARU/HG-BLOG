package com.hg.hgblogback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.dto.response.search.GetPopularListResponseDTO;
import com.hg.hgblogback.dto.response.search.GetRelationListResponseDTO;
import com.hg.hgblogback.repository.SearchLogRepository;
import com.hg.hgblogback.repository.resultSet.GetPopularListResultSet;
import com.hg.hgblogback.repository.resultSet.GetRelationListResultSet;
import com.hg.hgblogback.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchLogRepository searchLogRepository;

    //# 인기 검색어 리스트
    @Override
    public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
        List<GetPopularListResultSet> resultSets = new ArrayList<>();
        try {
            resultSets = searchLogRepository.getPopularList();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetPopularListResponseDTO.success(resultSets);
    } //* getPopularList */

    //# 연관 검색어 리스트
	@Override
	public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord) {
		 List<GetRelationListResultSet> resultSets = new ArrayList<>();
         try {
            resultSets = searchLogRepository.getRelationList(searchWord);
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
         }
         return GetRelationListResponseDTO.success(resultSets);
	} //* getRelationList */
    
}
