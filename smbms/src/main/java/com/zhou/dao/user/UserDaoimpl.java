package com.zhou.dao.user;

import com.zhou.dao.BaseDao;
import com.zhou.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoimpl implements UserDao{

    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement ps=null;
        ResultSet rs=null;
        User user=null;
        if(connection!=null){
            String sql="Select * from smbms_user where userCode=?";
            Object[] params= {userCode};
           rs= BaseDao.excute(connection, ps, rs, sql, params);
            while(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,ps,rs);
        }



        return user;
    }
}
