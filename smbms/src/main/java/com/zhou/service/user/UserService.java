package com.zhou.service.user;

import com.zhou.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);

}
