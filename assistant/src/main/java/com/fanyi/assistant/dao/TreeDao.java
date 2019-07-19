package com.fanyi.assistant.dao;

import com.fanyi.assistant.model.TagTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TreeDao {

    List<TagTree> getTreeData(@Param("tableName") String tableName);
}
