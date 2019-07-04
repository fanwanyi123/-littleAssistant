package com.fanyi.assistant.service;



import com.fanyi.assistant.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    /**
     * 通过用户名查找是否存在
     *
     * @param userName userName
     * @param pwd      pwd
     * @return
     */
    public boolean findUserByInputVal(String userName, String pwd);

    /**
     * 注册用户
     *
     * @param user user
     */
    public void insertUserInfo(User user);


    /**
     * 查询用户名是否存在
     * @param userName userName
     * @return
     */
    public boolean findUserByUserName(String userName);



    public Map<String, Object> redisMap();

    /**
     * 根据接口查询所用的用户
     */
    public List<User> findAllUser();
}
