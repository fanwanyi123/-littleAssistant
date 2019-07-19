package com.fanyi.assistant.service;

import com.fanyi.assistant.model.TagTree;

import java.util.List;

public interface TreeService {

    List<TagTree> getTreeData(String tableName);
}
