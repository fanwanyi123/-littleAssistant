package com.fanyi.assistant.service.impl;

import com.fanyi.assistant.dao.TreeDao;
import com.fanyi.assistant.model.TagTree;
import com.fanyi.assistant.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/1712:00
 * @Version 1.0.1
 **/
@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    TreeDao treeDao;

    @Override
    public List<TagTree> getTreeData(String tableName) {
        return  treeDao.getTreeData(tableName);
    }
}
