package com.zhou.dao;



import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;



    static{
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");
    }

//获取数据库连接
    public static Connection getconnection(){
        Connection connection=null;
        try {
            Class.forName(driver);
             connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

//编写查询公共类
    public static ResultSet excute(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length ; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }

        resultSet=preparedStatement.executeQuery();
        return resultSet;
    }
//编写增删改公共方法
public static int excute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] params) throws SQLException {
    preparedStatement = connection.prepareStatement(sql);

    for (int i = 0; i < params.length ; i++) {
        preparedStatement.setObject(i+1,params[i]);
    }

    int updataRows=preparedStatement.executeUpdate();
    return updataRows;
}

//释放资源
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag=true;

        if(resultSet!=null){
            try {
                resultSet.close();
                //GC回收
                resultSet=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
                preparedStatement=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(connection!=null){
            try {
                connection.close();
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }


}
