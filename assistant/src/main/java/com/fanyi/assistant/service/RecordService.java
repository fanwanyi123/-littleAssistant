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
     * 添加记录
     *
     * @param record 记录
     */
    Integer insertRecord(Record record);

    /**
     * 修改记录详细信息
     * @param record 记录
     */
    void updateRecordDetail(Record record);
    /**
     * 删除记录
     * @param id 记录ID
     */
    void deleteRecord(Integer id);

    /**
     * 分页显示
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 记录列表
     */
    PageInfo<Record> pageArticle(Integer pageIndex,
                                 Integer pageSize,
                                 HashMap<String, Object> criteria);


    /**
     * 记录详情页面显示
     * @param status 状态
     * @param id 章ID
     * @return 记录
     */
    Record getRecordByStatusAndId(Integer status, Integer id);


    /**
     * 获得上一篇记录
     * @param id 记录ID
     * @return 记录
     */
    Record getAfterRecord(Integer id);

    /**
     * 获得下一篇记录
     * @param id 记录ID
     * @return 记录
     */
    Record getPreRecord(Integer id);

    /**
     * 修改记录简单信息
     *
     * @param record 记录
     */
    void updateRecord(Record record);

}
