package com.fanyi.assistant.dao;


import com.fanyi.assistant.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao {
	
	public List<User> findAll();


	public Integer findUserByInputVal(@Param("userName") String userName, @Param("pwd") String pwd);

	public void insertUserInfo(User user);

    public Integer findUserByUserName(@Param("userName") String userName);
}
