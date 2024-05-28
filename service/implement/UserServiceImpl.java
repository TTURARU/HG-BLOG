package com.hg.hgblogback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hg.hgblogback.dto.request.user.EditNicknameRequestDTO;
import com.hg.hgblogback.dto.request.user.EditProfileImageRequestDTO;
import com.hg.hgblogback.dto.response.ResponseDTO;
import com.hg.hgblogback.dto.response.user.EditNicknameResponseDTO;
import com.hg.hgblogback.dto.response.user.EditProfileImageResponseDTO;
import com.hg.hgblogback.dto.response.user.GetLoginUserReponseDTO;
import com.hg.hgblogback.dto.response.user.GetUserResponseDTO;
import com.hg.hgblogback.entity.UserEntity;
import com.hg.hgblogback.repository.UserRepository;
import com.hg.hgblogback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //# 특정 유저 정보
    @Override
    public ResponseEntity<? super GetUserResponseDTO> getUser(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return GetUserResponseDTO.nonExistentUser();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetUserResponseDTO.success(userEntity);
    }

    //# 현재 로그인한 유저 정보
    @Override
    public ResponseEntity<? super GetLoginUserReponseDTO> getLoginUser(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return GetLoginUserReponseDTO.nonExistentUser();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetLoginUserReponseDTO.success(userEntity);
    }

    //# 닉네임 수정
    @Override
    public ResponseEntity<? super EditNicknameResponseDTO> editNickname(EditNicknameRequestDTO dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) EditNicknameResponseDTO.nonExistentUser();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return EditNicknameResponseDTO.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return EditNicknameResponseDTO.success();
    }

    //# 프로필 이미지 수정
    @Override
    public ResponseEntity<? super EditProfileImageResponseDTO> editProfileImage(EditProfileImageRequestDTO dto,
            String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return EditProfileImageResponseDTO.nonExistentUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return EditProfileImageResponseDTO.success();
    }
    
}
