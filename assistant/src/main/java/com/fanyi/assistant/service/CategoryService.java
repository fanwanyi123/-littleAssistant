package com.fanyi.assistant.service;


import com.fanyi.assistant.model.Category;

import java.util.List;

/**
 * @author fanyi
 */
public interface CategoryService {

    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategory();

    /**
     * 添加分类
     *
     * @param category 分类
     * @return 分类
     */
    Category insertCategory(Category category);

    /**
     * 更新分类
     *
     * @param category 分类
     */
    void updateCategory(Category category);



}
