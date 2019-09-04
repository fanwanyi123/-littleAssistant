package com.fanyi.assistant.service.impl;

import com.fanyi.assistant.dao.UploadFileDao;
import com.fanyi.assistant.model.UploadFile;
import com.fanyi.assistant.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/8/298:08
 * @Version 1.0.1
 **/
@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
    /**
     * 文件保存目录，物理路径
     */
    public final String rootPath = "E:/Upload";

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    @Autowired
    UploadFileDao uploadFileDao;

    @Override
    public int uploadFile(MultipartFile[] files, Integer recordId) {
        //1.文件后缀过滤，只允许部分后缀
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            //文件的完整名称,如spring.jpeg
            String filename = file.getOriginalFilename();
            if ("".equals(filename)) {
                continue;
            }
            //文件名,如spring
            String name = filename.substring(0, filename.indexOf("."));
            //文件后缀,如.jpeg
            String suffix = filename.substring(filename.lastIndexOf("."));
            String fileIcon = getFileIcon(suffix.toLowerCase());
            //2.创建文件目录
            //创建年月文件夹
            Calendar date = Calendar.getInstance();
            File dateDirs = new File(date.get(Calendar.YEAR)
                    + File.separator + (date.get(Calendar.MONTH) + 1));

            //目标文件
            File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);
            int i = 1;
            //若文件存在重命名
            String newFilename = filename;
            while (descFile.exists()) {
                newFilename = name + "(" + i + ")" + suffix;
                String parentPath = descFile.getParent();
                descFile = new File(parentPath + File.separator + newFilename);
                i++;
            }
            //判断目标文件所在的目录是否存在
            if (!descFile.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                descFile.getParentFile().mkdirs();
            }

            //3.存储文件
            //将内存中的数据写入磁盘
            try {
                file.transferTo(descFile);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("上传失败，cause:{}", e);
            }
            //完整的url
            String fileUrl = rootPath + dateDirs + File.separator + newFilename;
            //4.返回URL
            String fileSize = getFileSizeStr(descFile);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setName(filename);
            uploadFile.setUrl(fileUrl);
            uploadFile.setSize(fileSize);
            uploadFile.setIcon(fileIcon);
            uploadFile.setRecordId(recordId);
            uploadFiles.add(uploadFile);
        }
        if (!uploadFiles.isEmpty()) {
            return uploadFileDao.insert(uploadFiles);
        } else {
            return -1;
        }
    }

    private String getFileIcon(String suffix) {
        switch (suffix){
            case ".zip":
                return "fa fa-file-archive-o";
            case ".rar":
                return "fa fa-file-archive-o";
            case ".txt":
                return "fa fa-file-text";
            case ".doc":
                return "fa fa-file-word-o";
            case ".docx":
                return "fa fa-file-word-o";
            case ".xlsx":
                return "fa fa-file-excel-o";

        }
        return null;
    }

    @Override
    public List<UploadFile> getRecordRefFile(Integer recordId) {
        return uploadFileDao.getRecordRefFile(recordId);
    }

    private String getFileSizeStr(File descFile) {
        String fileSizeVal = "";
        long fileSize = descFile.length();
        long mb = 1024 * 1024;
        long kb = 1024;
        if (fileSize > mb) {
            fileSizeVal = saveOneBitTwoRound((double) fileSize / (1024 * 1024)) + "MB";
        } else if (fileSize > kb) {
            fileSizeVal = saveOneBitTwoRound((double) fileSize / 1024) + "KB";
        }else {
            fileSizeVal = fileSize + "Byte";
        }
        return fileSizeVal;
    }

    /**
     * 保留两位小数,进行四舍五入
     * @param d
     * @return
     */
    public static Double saveOneBitTwoRound(Double d){
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return tem;
    }

}
