package com.fanyi.assistant.controller;

import com.fanyi.assistant.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author fanyi
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    /**
     * 文件保存目录，物理路径
     */
    public final String rootPath = "E:/Upload";

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    @Autowired
    UploadFileService uploadFileService;

    /**
     * 上传文件
     *
     * @param files files
     * @return
     * @throws IOException IOException
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam("file") MultipartFile[] files) {
        return uploadFileService.uploadFile(files,null);
    }
}