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

    private Integer articleId;

    private String articleUserId;

    private String articleTitle;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private String articleCreateTime;

    private String articleUpdateTime;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private String articleContent;

    private String articleSummary;

    private List<Category> categoryList;

}