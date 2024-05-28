package com.hg.hgblogback.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.repository.resultSet.GetPopularListResultSet;

import lombok.Getter;

@Getter
public class GetPopularListResponseDTO extends ResponseDTO {
    private List<String> popularWordList;

    //# 생성자
    private GetPopularListResponseDTO(List<GetPopularListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> popularWordList = new ArrayList<>();
        for(GetPopularListResultSet resultSet: resultSets){
            String popularWord = resultSet.getSearchWord();
            popularWordList.add(popularWord);
        }

        this.popularWordList = popularWordList;
    }

    //# success
    public static ResponseEntity<GetPopularListResponseDTO> success(List<GetPopularListResultSet> resultSets) {
        GetPopularListResponseDTO result = new GetPopularListResponseDTO(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } 
}
