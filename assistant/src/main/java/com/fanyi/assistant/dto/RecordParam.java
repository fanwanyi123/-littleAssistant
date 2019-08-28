package com.fanyi.assistant.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fanyi
 */
@Data
public class RecordParam {

    private Integer recordId;

    private String recordTitle;

    private String recordContent;

    private String recordParentCategoryName;

    private String recordChildCategoryName;

    private String recordGrandsonCategoryName;

    private Integer recordOrder;

    private Integer recordStatus;

    private MultipartFile[] files;

}
