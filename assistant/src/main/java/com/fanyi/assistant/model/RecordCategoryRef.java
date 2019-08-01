package com.fanyi.assistant.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类关联表
 *
 * @author liuyanzhao
 */
@Data
public class RecordCategoryRef implements Serializable{

    private static final long serialVersionUID = -6809206515467725995L;

    private Integer recordId;

    private Integer categoryId;

    public RecordCategoryRef() {
    }

    public RecordCategoryRef(Integer recordId, Integer categoryId) {
        this.recordId = recordId;
        this.categoryId = categoryId;
    }
}