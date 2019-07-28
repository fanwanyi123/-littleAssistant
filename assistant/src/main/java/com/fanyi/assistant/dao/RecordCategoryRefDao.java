package com.fanyi.assistant.dao;


import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.model.RecordCategoryRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 记录分类关联表Mapper
 * @author fanyi
 */
@Mapper
public interface RecordCategoryRefDao {
    
    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    int insert(RecordCategoryRef record);

    /**
     * 根据文章ID获得分类列表
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);
}