package com.hg.hgblogback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hg.hgblogback.dto.request.auth.LoginReqeustDTO;
import com.hg.hgblogback.dto.request.auth.SignUpRequestDTO;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.dto.response.auth.LoginResponseDTO;
import com.hg.hgblogback.dto.response.auth.SignUpResponseDTO;
import com.hg.hgblogback.entity.UserEntity;
import com.hg.hgblogback.provider.JwtProvider;
import com.hg.hgblogback.repository.UserRepository;
import com.hg.hgblogback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //# 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto) {
        try {
            String email = dto.getEmail();
            boolean isEmailExists = userRepository.existsByEmail(email);
            if(isEmailExists) return SignUpResponseDTO.duplicateEmail();

            String nickname = dto.getNickname();
            boolean isNicknameExists = userRepository.existsByNickname(nickname);
            if(isNicknameExists) return SignUpResponseDTO.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean isTelNumberExists = userRepository.existsByTelNumber(telNumber);
            if(isTelNumberExists) return SignUpResponseDTO.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SignUpResponseDTO.success();
    } //* signUp */

    //# 로그인
    @Override
    public ResponseEntity<? super LoginResponseDTO> login(LoginReqeustDTO dto) {
        String token = null;

        try {
            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return LoginResponseDTO.loginFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isPasswordMatching = passwordEncoder.matches(password, encodedPassword);
            if(!isPasswordMatching) return LoginResponseDTO.loginFailed();

            token = jwtProvider.jwtCreate(email);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return LoginResponseDTO.success(token);
    } //* login */
} //* AuthServiceImpl */
