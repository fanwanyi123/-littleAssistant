package com.fanyi.assistant.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    int uploadFile(MultipartFile[] files,Integer recordId);
}
