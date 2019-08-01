package com.fanyi.assistant.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanyi
 */
@Data
public class Record implements Serializable{

    private static final long serialVersionUID = 5207865247400761539L;

    private Integer recordId;

    private String recordUserId;

    private String recordTitle;

    private Integer recordViewCount;

    private Integer recordCommentCount;

    private Integer recordLikeCount;

    private String recordCreateTime;

    private String recordUpdateTime;

    private Integer recordIsComment;

    private Integer recordStatus;

    private Integer recordOrder;

    private String recordContent;

    private String recordSummary;

    private List<Category> categoryList;

}