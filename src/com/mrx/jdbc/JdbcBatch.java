package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcBatch {

	public JdbcUtil jdbcUtil = new JdbcUtil();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcBatch jdbcBatch = new JdbcBatch();
		jdbcBatch.batch();
	}

	public void batch() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = jdbcUtil.getConnection();
			String sql1 = "insert into user(id,username,password) values(2,'jon','jon123')";
			String sql3 = "insert into user(id,username,password) values(3,'jon','jon123')";
			String sql2 = "insert into user(id,username,password) values(4,'jon','jon123')";
			
			statement = connection.createStatement();
			//添加要批量执行的SQL
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.addBatch(sql3);
			//执行批处理SQL语句
			int[] result = statement.executeBatch();
			//清除批处理命令
			statement.clearBatch();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}
	
	public void batch_prepared() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = jdbcUtil.getConnection();
			String sql = "insert into user(id,username,password) values(?,?,?)";
			
			statement = connection.prepareStatement(sql);
			//添加要批量执行的SQL,建议放在for循环中
			statement.setInt(1, 2);
			statement.setString(2, "jon");
			statement.setString(3, "jon123");
			statement.addBatch();//每次addBatch()后都会刷新
			
			
			
			//执行批处理SQL语句
			int[] result = statement.executeBatch();
			//清除批处理命令
			statement.clearBatch();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}
}
