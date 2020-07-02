package com.fanyi.assistant.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanyi.assistant.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author fanyi
 */
@Slf4j
@RestController
public class UploadFileController {

    @Autowired
    UploadFileService uploadFileService;

    /**
     * 上传文件
     *
     * @param files files
     * @return
     * @throws IOException IOException
     */
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam("file") JSONObject obj) {
        MultipartFile[] files = (MultipartFile[]) obj.get("file");
        return uploadFileService.uploadFile(files, null);
    }

    @GetMapping(value = "/download/file")
    public void  download(@RequestParam("path") String path, HttpServletResponse response) {
        try {
            // path： 欲下载的文件的路径
            File file = new File(path);
            // 获取文件名 - 设置字符集
            String downloadFileName = new String(file.getName().getBytes(StandardCharsets.UTF_8), "iso-8859-1");
            // 以流的形式下载文件
            InputStream fis;
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}