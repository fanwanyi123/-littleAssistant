package com.fanyi.assistant.dao;

import com.fanyi.assistant.model.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上传文件
 */
@Mapper
public interface UploadFileDao {
    /**
     * 添加上传文件记录
     * @param files files
     * @return 影响行数
     */
    int insert(@Param("list") List<UploadFile> files);
}
