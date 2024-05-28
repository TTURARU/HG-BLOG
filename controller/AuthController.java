package com.hg.hgblogback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hg.hgblogback.dto.request.auth.LoginReqeustDTO;
import com.hg.hgblogback.dto.request.auth.SignUpRequestDTO;
import com.hg.hgblogback.dto.response.auth.LoginResponseDTO;
import com.hg.hgblogback.dto.response.auth.SignUpResponseDTO;
import com.hg.hgblogback.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //# 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDTO> signUp(
        @RequestBody @Valid SignUpRequestDTO requestBody
    ){
        ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(requestBody);
        return response;
    } //* signUp */

    //# 로그인
    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDTO> login(
        @RequestBody @Valid LoginReqeustDTO reqeustBody
    ){
        ResponseEntity<? super LoginResponseDTO> response = authService.login(reqeustBody);
        return response;
    } //* login */
}
