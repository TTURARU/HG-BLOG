package com.hg.hgblogback.service;

import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.dto.request.user.EditNicknameRequestDTO;
import com.hg.hgblogback.dto.request.user.EditProfileImageRequestDTO;
import com.hg.hgblogback.dto.response.user.EditNicknameResponseDTO;
import com.hg.hgblogback.dto.response.user.EditProfileImageResponseDTO;
import com.hg.hgblogback.dto.response.user.GetLoginUserReponseDTO;
import com.hg.hgblogback.dto.response.user.GetUserResponseDTO;

public interface UserService {
    //# 특정 유저 정보
    ResponseEntity<? super GetUserResponseDTO> getUser(String email);
    //# 현재 로그인한 유저 정보
    ResponseEntity<? super GetLoginUserReponseDTO> getLoginUser(String email);
    //# 닉네임 수정
    ResponseEntity<? super EditNicknameResponseDTO> editNickname(EditNicknameRequestDTO dto, String email);
    //# 프로필 이미지 수정
    ResponseEntity<? super EditProfileImageResponseDTO> editProfileImage(EditProfileImageRequestDTO dto, String email);
}
