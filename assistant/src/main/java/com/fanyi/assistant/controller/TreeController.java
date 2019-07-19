package com.fanyi.assistant.controller;

import com.fanyi.assistant.model.TagTree;
import com.fanyi.assistant.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/1622:43
 * @Version 1.0.1
 **/
@Controller
public class TreeController {
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @RequestMapping(value = "getTreeData")
    public Object getTreeData() {
        List<TagTree> treeList = treeService.getTreeData("tag_info");
        return treeList;
    }
}
