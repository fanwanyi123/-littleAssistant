package com.fanyi.assistant.controller;

import cn.hutool.http.HtmlUtil;
import com.fanyi.assistant.dto.RecordParam;
import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.model.Record;
import com.fanyi.assistant.service.CategoryService;
import com.fanyi.assistant.service.RecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author fanyi
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RecordService recordService;


    /**
     * 做记录页面显示
     *
     * @return
     */
    @RequestMapping(value = "/getCategory")
    @ResponseBody
    public Object getCategory() {
        List<Category> categoryList = categoryService.listCategory();
        return categoryList;
    }

    /**
     * 后台添加文章提交操作
     * @param articleParam articleParam
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, RecordParam articleParam) {
        Record record = new Record();
        //用户ID
        String userName = (String) session.getAttribute("userName");
        record.setArticleUserId(userName);
        record.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            record.setArticleSummary(summary);
        } else {
            record.setArticleSummary(summaryText);
        }
        record.setArticleContent(articleParam.getArticleContent());
        record.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            Category category = new Category();
            category.setId(articleParam.getArticleParentCategoryId());
            categoryList.add(category);
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            Category category = new Category();
            category.setId(articleParam.getArticleChildCategoryId());
            categoryList.add(category);
        }
        if (articleParam.getArticleGrandsonCategoryId() != null) {
            Category category = new Category();
            category.setId(articleParam.getArticleGrandsonCategoryId());
            categoryList.add(category);
        }
        record.setCategoryList(categoryList);
        recordService.insertRecord(record);
        return "redirect:/record";
    }


    /**
     * 后台文章列表显示
     * @return modelAndView
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Object dataList(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        PageInfo<Record> articlePageInfo = recordService.pageArticle(pageIndex, pageSize, criteria);
        return articlePageInfo;
    }

    /**
     * 后台文章列表显示
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public String index() {
        return "record";
    }
}



