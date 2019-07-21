package com.fanyi.assistant.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author fanyi
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 6687286913317513141L;

    private Integer categoryId;

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

}