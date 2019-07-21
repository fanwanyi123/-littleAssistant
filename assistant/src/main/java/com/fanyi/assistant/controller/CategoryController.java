package com.fanyi.assistant.controller;


import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author fanyi
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 后台添加分类提交
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category)  {
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    /**
     * 编辑分类提交
     *
     * @param category 分类
     * @return 重定向
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category)  {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
