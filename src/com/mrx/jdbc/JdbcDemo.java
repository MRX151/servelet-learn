package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDemo {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		JdbcDemo jdbcDemo = new JdbcDemo();
		jdbcDemo.testConnect();
	}

	//测试连接
	public void testConnect() throws Exception{
        //要连接的数据库URL
        //String url = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
		//如果是本地默认端口号，可以不写
        String url = "jdbc:mysql:///mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        //连接的数据库时使用的用户名
        String username = "root";
        //连接的数据库时使用的密码
        String password = "root";
        
        //1.加载驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//推荐使用这种方式来加载驱动
        //2.获取与数据库的链接
        Connection conn = DriverManager.getConnection(url, username, password);
        
        //3.获取用于向数据库发送sql语句的statement
        Statement st = conn.createStatement();
        
        String sql = "select * from student";
        //4.向数据库发sql,并获取代表结果集的resultset
        ResultSet rs = st.executeQuery(sql);
        
        //5.取出结果集的数据
        while(rs.next()){
            System.out.println("id=" + rs.getObject("id"));
            System.out.println("name=" + rs.getObject("name"));
            System.out.println("lesson_id=" + rs.getObject("lesson_id"));
            System.out.println("grade=" + rs.getObject("grade"));
            System.out.println("birth=" + rs.getObject("birth"));
        }
        
        //6.关闭链接，释放资源
        rs.close();
        st.close();
        conn.close();
	}
}
