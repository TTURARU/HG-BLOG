package com.hg.hgblogback.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.common.ResponseCode;
import com.hg.hgblogback.common.ResponseMessage;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.repository.resultSet.GetRelationListResultSet;

import lombok.Getter;

@Getter
public class GetRelationListResponseDTO extends ResponseDTO {
    private List<String> relationWordList;

    //# 생성자
    private GetRelationListResponseDTO(List<GetRelationListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> relationWordList = new ArrayList<>();
        for(GetRelationListResultSet resultSet: resultSets){
            String relationWord = resultSet.getSearchWord();
            relationWordList.add(relationWord);
        }
        this.relationWordList = relationWordList;
    }

    //# success
    public static ResponseEntity<GetRelationListResponseDTO> success(List<GetRelationListResultSet> resultSets) {
        GetRelationListResponseDTO result = new GetRelationListResponseDTO(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
