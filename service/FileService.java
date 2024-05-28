package com.hg.hgblogback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    //# 업로드된 파일 처리 및 저장
    String upload(MultipartFile file);
    //# 저장된 이미지 가져오기
    Resource getImage(String fileName);
}
