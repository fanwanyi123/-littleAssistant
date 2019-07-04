package com.fanyi.assistant.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Integer id;	
	private String userName;
	private String password;
	private String trueName;
	private String email;
	private String createTime;
	private String role;



}
