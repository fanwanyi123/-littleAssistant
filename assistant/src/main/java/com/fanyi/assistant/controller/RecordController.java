package com.fanyi.assistant.controller;

import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author fanyi
 */
@Controller
public class RecordController {

    @Autowired
    private CategoryService categoryService;



    /**
     * 做记录页面显示
     *
     * @return
     */
    @RequestMapping(value = "/recordInsert")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        return "recordInsert";
    }


}



