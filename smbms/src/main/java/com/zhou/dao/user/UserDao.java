package com.zhou.dao.user;

import com.zhou.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(Connection connection,String userCode) throws SQLException;


}
