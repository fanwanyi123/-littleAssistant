package com.fanyi.assistant.service.impl;

import com.fanyi.assistant.dao.CategoryDao;
import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author fanyi
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryDao categoryDao;

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
    public Category getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }

    @Override
    public Category insertCategory(Category category) {
        try {
            int categoryId = categoryDao.getMaxCategoryId() == null ? 0 : categoryDao.getMaxCategoryId();
            category.setId(categoryId + 1);
            categoryDao.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建分类失败, category:{}, cause:{}", category, e);
        }
        return category;
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
    public Category getCategoryById(Integer id) {
        return categoryDao.getCategoryById(id);
    }
}
