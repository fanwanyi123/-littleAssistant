package com.fanyi.assistant.dto;

import lombok.Data;

/**
 * @author fanyi
 */
@Data
public class RecordParam {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleGrandsonCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

}
