package com.fanyi.assistant.dao;


import com.fanyi.assistant.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao {
	
	 List<User> findAll();

	 Integer findUserByInputVal(@Param("userName") String userName, @Param("pwd") String pwd);

	 void insertUserInfo(User user);

     Integer findUserByUserName(@Param("userName") String userName);
}
