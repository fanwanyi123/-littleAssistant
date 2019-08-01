package com.fanyi.assistant.service;


import com.fanyi.assistant.model.Record;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * 记录Service
 * @author fanyi
 */
public interface RecordService {
    /**
     * 添加文章
     *
     * @param record 文章
     */
    void insertRecord(Record record);

    /**
     * 删除文章
     * @param id 文章ID
     */
    void deleteRecord(Integer id);

    /**
     * 分页显示
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Record> pageArticle(Integer pageIndex,
                                 Integer pageSize,
                                 HashMap<String, Object> criteria);


    /**
     * 文章详情页面显示
     * @param status 状态
     * @param id 章ID
     * @return 文章
     */
    Record getRecordByStatusAndId(Integer status, Integer id);


    /**
     * 获得上一篇文章
     * @param id 文章ID
     * @return 文章
     */
    Record getAfterRecord(Integer id);

    /**
     * 获得下一篇文章
     * @param id 文章ID
     * @return 文章
     */
    Record getPreRecord(Integer id);
}
