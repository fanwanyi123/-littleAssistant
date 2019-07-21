package com.fanyi.assistant.service.impl;

import com.fanyi.assistant.dao.CategoryDao;
import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author fanyi
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryDao categoryDao;

    @Override
    public Category getCategoryById(Integer id) {
        Category category = null;
        try {
            category = categoryDao.getCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类ID获得分类, id:{}, cause:{}", id, e);
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryDao.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
    }

    @Override
    public Category insertCategory(Category category) {
        try {
            categoryDao.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建分类失败, category:{}, cause:{}", category, e);
        }
        return category;
    }


    @Override
    public Integer countCategory() {
        Integer count = 0;
        try {
            count = categoryDao.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计分类失败, cause:{}", e);
        }
        return count;
    }


    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.listCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categoryList;
    }


    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryDao.getCategoryByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
        return category;
    }


}
