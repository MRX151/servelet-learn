package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcMetaData {

	JdbcUtil jdbcUtil = new JdbcUtil();
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		JdbcMetaData jdbcMetaData = new JdbcMetaData();
//		jdbcMetaData.getConnectionMeta();
		jdbcMetaData.getResultSetMeta();
	}

	public void getConnectionMeta() throws SQLException {
		Connection connection = jdbcUtil.getConnectionFromDataSource();
		DatabaseMetaData metadata = connection.getMetaData();
        //getURL()：返回一个String类对象，代表数据库的URL
        System.out.println(metadata.getURL());
        //getUserName()：返回连接当前数据库管理系统的用户名
        System.out.println(metadata.getUserName());
        //getDatabaseProductName()：返回数据库的产品名称
        System.out.println(metadata.getDatabaseProductName());
        //getDatabaseProductVersion()：返回数据库的版本号
        System.out.println(metadata.getDatabaseProductVersion());
        //getDriverName()：返回驱动驱动程序的名称
        System.out.println(metadata.getDriverName());
        //getDriverVersion()：返回驱动程序的版本号
        System.out.println(metadata.getDriverVersion());
        //isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作
        System.out.println(metadata.isReadOnly());
        jdbcUtil.release(connection, null, null);
	}
	
	public void getResultSetMeta() throws SQLException {
		Connection connection = jdbcUtil.getConnectionFromDataSource();
		Statement statement = connection.createStatement();
		String sql = "select * from student";
		ResultSet resultSet = statement.executeQuery(sql);
        //ResultSet.getMetaData()获得代表ResultSet对象元数据的ResultSetMetaData对象
        ResultSetMetaData metadata = resultSet.getMetaData();
        //getColumnCount() 返回resultset对象的列数
        System.out.println(metadata.getColumnCount());
        //getColumnName(int column) 获得指定列的名称
        System.out.println(metadata.getColumnName(1));
        //getColumnTypeName(int column)获得指定列的类型
        System.out.println(metadata.getColumnTypeName(1));
		resultSet.getMetaData();
		
		jdbcUtil.release(connection, statement, resultSet);
	}
}
