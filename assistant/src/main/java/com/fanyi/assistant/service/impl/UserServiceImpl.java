package com.fanyi.assistant.service.impl;


import com.fanyi.assistant.dao.UserDao;
import com.fanyi.assistant.model.User;
import com.fanyi.assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserDao userDao;


    @Override
    public boolean findUserByInputVal(String userName, String pwd) {
        Integer existUser = userDao.findUserByInputVal(userName,pwd);
        if (existUser==null){
            return  false;
        }else {
            return existUser>0;
        }
    }

	@Override
	public void insertUserInfo(User user) {
        userDao.insertUserInfo(user);
	}

    @Override
    public boolean findUserByUserName(String userName) {
        Integer existUser = userDao.findUserByUserName(userName);
        if (existUser==null){
            return  false;
        }else {
            return existUser>0;
        }

    }

    @Override
	public Map<String, Object> redisMap() {
		return null;
	}

    @Override
    public List<User> findAllUser() {
        System.out.println("测试缓存，第二次查询不走方法!!!");
        List<User> list = userDao.findAll();
        return list;
    }


}
