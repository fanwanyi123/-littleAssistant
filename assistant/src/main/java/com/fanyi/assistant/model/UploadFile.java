package com.fanyi.assistant.model;

import lombok.Data;

/**
 * @author fanyi
 */

@Data
public class UploadFile {
    private Integer id;
    
    private String url;

    private String name;

    private Integer recordId;

}
