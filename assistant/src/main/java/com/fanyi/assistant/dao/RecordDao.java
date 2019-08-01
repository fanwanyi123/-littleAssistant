package com.fanyi.assistant.dao;

import com.fanyi.assistant.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据ID删除
     * @param recordId recordId
     * @return 影响函数
     */
    Integer deleteById(Integer recordId);
    /**
     * 获得所有的文章
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Record> findAll(HashMap<String, Object> criteria);


    /**
     * 根据id查询记录信息
     * @param status 状态
     * @param id 文章ID
     * @return 文章
     */
    Record getRecordByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * 获得上一篇文章
     * @param id 文章ID
     * @return 文章
     */
    Record getAfterRecord(@Param(value = "id") Integer id);


    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Record getPreRecord(@Param(value = "id") Integer id);
}