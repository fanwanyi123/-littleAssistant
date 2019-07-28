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
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Record> pageArticle(Integer pageIndex,
                                 Integer pageSize,
                                 HashMap<String, Object> criteria);
}
