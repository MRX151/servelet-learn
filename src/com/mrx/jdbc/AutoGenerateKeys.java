package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutoGenerateKeys {
	
	private JdbcUtil jdbcUtil = new JdbcUtil();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoGenerateKeys autoGenerateKeys = new AutoGenerateKeys();
//		autoGenerateKeys.basic_generate_key();
		autoGenerateKeys.basic_prepared_generate_key();
	}

	
	public void basic_generate_key() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = jdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "insert into user(username,password) values('max','max123')";
			int update = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			
			while (resultSet.next()) {
				System.out.println("生成的主键id:" + resultSet.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}
	
	public void basic_prepared_generate_key() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = jdbcUtil.getConnection();
			String sql = "insert into user(username,password) values(?,?)";
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, "宋江");
			statement.setString(2, "宋江123");
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			while (resultSet.next()) {
				System.out.println("生成的主键id:" + resultSet.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}

	
}
