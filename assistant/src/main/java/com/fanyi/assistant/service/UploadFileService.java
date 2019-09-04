package com.fanyi.assistant.service;

import com.fanyi.assistant.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFileService {
    int uploadFile(MultipartFile[] files,Integer recordId);

    List<UploadFile>  getRecordRefFile(Integer recordId);
}
