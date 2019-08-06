package com.fanyi.assistant.dto;

import lombok.Data;

/**
 * @author fanyi
 */
@Data
public class RecordParam {

    private Integer recordId;

    private String recordTitle;

    private String recordContent;

    private Integer recordParentCategoryId;

    private Integer recordChildCategoryId;

    private Integer recordGrandsonCategoryId;

    private Integer recordOrder;

    private Integer recordStatus;

}
