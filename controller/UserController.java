package com.hg.hgblogback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hg.hgblogback.dto.request.user.EditNicknameRequestDTO;
import com.hg.hgblogback.dto.request.user.EditProfileImageRequestDTO;
import com.hg.hgblogback.dto.response.user.EditNicknameResponseDTO;
import com.hg.hgblogback.dto.response.user.EditProfileImageResponseDTO;
import com.hg.hgblogback.dto.response.user.GetLoginUserReponseDTO;
import com.hg.hgblogback.dto.response.user.GetUserResponseDTO;
import com.hg.hgblogback.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //# 특정 유저 정보
    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponseDTO> getUser(
        @PathVariable("email") String email
    ){
        ResponseEntity<? super GetUserResponseDTO> response = userService.getUser(email);
        return response;
    } //* getUser */

    //# 현재 로그인한 유저 정보
    @GetMapping("")
    public ResponseEntity<? super GetLoginUserReponseDTO> getLoginUser(
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetLoginUserReponseDTO> response = userService.getLoginUser(email);
        return response;
    } //* getLoginUser */

    //# 닉네임 수정
    @PatchMapping("/nickname")
    public ResponseEntity<? super EditNicknameResponseDTO> editNickname(
        @RequestBody @Valid EditNicknameRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super EditNicknameResponseDTO> response = userService.editNickname(requestBody, email);
        return response;
    } //* editNickname */

    //# 프로필 이미지 수정
    @PatchMapping("/profile-image")
    public ResponseEntity<? super EditProfileImageResponseDTO> editProfileImage(
        @RequestBody @Valid EditProfileImageRequestDTO requestBody,
        @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super EditProfileImageResponseDTO> response = userService.editProfileImage(requestBody, email);
        return response;
    } //* editProfileImage */
}
