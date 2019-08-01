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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        record.setRecordUserId(userName);
        record.setRecordTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            record.setRecordSummary(summary);
        } else {
            record.setRecordSummary(summaryText);
        }
        record.setRecordContent(articleParam.getArticleContent());
        record.setRecordStatus(articleParam.getArticleStatus());
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


    /**
     * 文章详情页显示
     * @param articleId 文章ID
     * @return modelAndView
     */
    @RequestMapping(value = "/{articleId}")
    @ResponseBody
    public Object getRecordDetailPage(@PathVariable("articleId") Integer articleId) {
        //文章信息
        Map<String,Object> detailMap = new HashMap<>();
        Record record = recordService.getRecordByStatusAndId(1, articleId);
        if (record == null) {
            detailMap.put("0",null);
            return detailMap;
        }
        //文章信息
        detailMap.put("record", record);
        //获取下一篇文章
        Record afterRecord = recordService.getAfterRecord(articleId);
        detailMap.put("afterRecord", afterRecord);

        //获取上一篇文章
        Record preRecord = recordService.getPreRecord(articleId);
        detailMap.put("preRecord", preRecord);
        return detailMap;
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @RequestMapping(value = "/delete/{id}")
    public void deleteRecord(@PathVariable("id") Integer id) {
        recordService.deleteRecord(id);
    }


    /**
     * 编辑文章页面显示
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editRecordView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Record record = recordService.getRecordByStatusAndId(null, id);
        modelAndView.addObject("record", record);
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("recordEdit");
        return modelAndView;
    }
}



