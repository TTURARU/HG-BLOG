package com.hg.hgblogback.service;

import org.springframework.http.ResponseEntity;

import com.hg.hgblogback.dto.request.auth.LoginReqeustDTO;
import com.hg.hgblogback.dto.request.auth.SignUpRequestDTO;
import com.hg.hgblogback.dto.response.auth.LoginResponseDTO;
import com.hg.hgblogback.dto.response.auth.SignUpResponseDTO;


public interface AuthService {
    //# 회원가입
    ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto);
    //# 로그인
    ResponseEntity<? super LoginResponseDTO> login(LoginReqeustDTO dto);
}
