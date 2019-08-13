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
        record.setRecordCreateTime(formatter.format(new Date()));
        String dateString = formatter.format(new Date());
        record.setRecordUpdateTime(dateString);
        record.setRecordViewCount(0);
        record.setRecordLikeCount(0);
        record.setRecordCommentCount(0);
        record.setRecordOrder(1);
        int recordId = recordDao.getMaxRecordId() == null ? 0 : recordDao.getMaxRecordId();
        record.setRecordId(recordId + 1);
        recordDao.insert(record);
        //添加分类和文章关联
        for (int i = 0; i < record.getCategoryList().size(); i++) {
            RecordCategoryRef recordCategoryRef = new RecordCategoryRef(record.getRecordId(), record.getCategoryList().get(i).getId());
            recordCategoryRefDao.insert(recordCategoryRef);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecordDetail(Record record) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setRecordUpdateTime(formatter.format(new Date()));
        recordDao.update(record);
        if (record.getCategoryList() != null) {
            //删除分类和文章关联
            recordCategoryRefDao.deleteByRecordId(record.getRecordId());
            //添加分类和文章关联
            for (int i = 0; i < record.getCategoryList().size(); i++) {
                RecordCategoryRef recordCategoryRef = new RecordCategoryRef(record.getRecordId(), record.getCategoryList().get(i).getId());
                recordCategoryRefDao.insert(recordCategoryRef);
            }
        }
    }

    @Override
    public void deleteRecord(Integer id) {
        recordDao.deleteById(id);
        recordCategoryRefDao.deleteByRecordId(id);
    }

    @Override
    public PageInfo<Record> pageArticle(Integer pageIndex,
                                        Integer pageSize,
                                        HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Record> recordList = recordDao.findAll(criteria);
        for (int i = 0; i < recordList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = recordCategoryRefDao.listCategoryByArticleId(recordList.get(i).getRecordId());
            recordList.get(i).setCategoryList(categoryList);
        }
        return new PageInfo<>(recordList);
    }


    @Override
    public Record getRecordByStatusAndId(Integer status, Integer id) {
        Record record = recordDao.getRecordByStatusAndId(status, id);
        if (record != null) {
            List<Category> categoryList = recordCategoryRefDao.listCategoryByArticleId(record.getRecordId());
            record.setCategoryList(categoryList);
        }
        return record;
    }

    @Override
    public Record getAfterRecord(Integer id) {
        return recordDao.getAfterRecord(id);
    }

    @Override
    public Record getPreRecord(Integer id) {
        return recordDao.getPreRecord(id);
    }
}
