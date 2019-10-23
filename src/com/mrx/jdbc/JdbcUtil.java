package com.mrx.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	
	private static JdbcDataSource dataSource = new JdbcDataSource();
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	//静态初始化代码块,会在类被第一次加载时执行
	static {
		try {
			//通过InputStream来读取properties文件
			 InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			 Properties prop = new Properties();
			 prop.load(in);//通过文件输入流，实例化properties
			 
			 driver = prop.getProperty("driver");
			 url = prop.getProperty("url");
			 username = prop.getProperty("username");
			 password = prop.getProperty("password");
			 
			 //加载驱动
			 Class.forName(driver);
			 
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}
	}
	/**
	 * 基础方式获取连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	
	public static Connection getConnectionFromDataSource() throws SQLException {
		return dataSource.getConnection();
	}
	
    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try{
                //关闭Connection数据库连接对象
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int update(String sql,String[] params) {
    	
    	Connection connection = null;
    	PreparedStatement statement = null;
    	
    	try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
    	
    	
    	return -1;
    }
}
