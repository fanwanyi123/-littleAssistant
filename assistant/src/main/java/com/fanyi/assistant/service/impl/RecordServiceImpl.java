package com.fanyi.assistant.service.impl;

import com.fanyi.assistant.dao.RecordCategoryRefDao;
import com.fanyi.assistant.dao.RecordDao;
import com.fanyi.assistant.model.Category;
import com.fanyi.assistant.model.Record;
import com.fanyi.assistant.model.RecordCategoryRef;
import com.fanyi.assistant.service.RecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 记录Servie实现
 *
 * @author fanyi
 */
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired(required = false)
    private RecordDao recordDao;
    @Autowired(required = false)
    private RecordCategoryRefDao recordCategoryRefDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRecord(Record record) {
        //添加文章
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setArticleCreateTime(formatter.format(new Date()));
        String dateString = formatter.format(new Date());
        record.setArticleUpdateTime(dateString);
        record.setArticleViewCount(0);
        record.setArticleLikeCount(0);
        record.setArticleCommentCount(0);
        record.setArticleOrder(1);
        recordDao.insert(record);
        //添加分类和文章关联
        for (int i = 0; i < record.getCategoryList().size(); i++) {
            RecordCategoryRef recordCategoryRef = new RecordCategoryRef(record.getArticleId(), record.getCategoryList().get(i).getId());
            recordCategoryRefDao.insert(recordCategoryRef);
        }
    }


    @Override
    public PageInfo<Record> pageArticle(Integer pageIndex,
                                         Integer pageSize,
                                         HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Record> articleList = recordDao.findAll(criteria);
        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = recordCategoryRefDao.listCategoryByArticleId(articleList.get(i).getArticleId());
            articleList.get(i).setCategoryList(categoryList);
        }
        return new PageInfo<>(articleList);
    }
}
