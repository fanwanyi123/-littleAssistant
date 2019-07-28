package com.fanyi.assistant.dao;

import com.fanyi.assistant.model.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 记录Dao
 * @author fanyi
 */
@Mapper
public interface RecordDao {
    /**
     * 添加记录
     *
     * @param record record
     * @return record
     */
    Integer insert(Record record);

    /**
     * 获得所有的文章
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Record> findAll(HashMap<String, Object> criteria);
}