package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTransaction {

	JdbcUtil jdbcUtil = new JdbcUtil();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcTransaction jdbcTransaction = new JdbcTransaction();
		jdbcTransaction.open_transaction();
	}

	public void open_transaction() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			//connection = jdbcUtil.getConnection();
			connection = jdbcUtil.getConnectionFromDataSource();//通过自定义连接池来获取connection
			//默认提交，通过设置antoCommit为false来开启事务
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			String sql1 = "update user set username = 'up熊' where id = 2";
			int update = statement.executeUpdate(sql1);
			System.out.println("执行成功，生效条目数："+update);
			

			String sql2 = "select username from user where id = 2";
			//用本connection来查询
			resultSet = statement.executeQuery(sql2);
			while(resultSet.next()) {
				System.out.println("通过本connection查询：id=2的username="+ resultSet.getString(1));
				//打印 通过本connection查询：id=2的username=up熊
			}
			//尝试用另一个connection来获取
			//Connection cn2 = jdbcUtil.getConnection();
			Connection cn2 = jdbcUtil.getConnectionFromDataSource();//通过自定义连接池来获取connection
			Statement st2 = cn2.createStatement();
			ResultSet rs2 = st2.executeQuery(sql2);
			while(rs2.next()) {
				System.out.println("尝试通过另一个connection来查询：id=2的username="+ rs2.getString(1));
				//打印 尝试通过另一个connection来查询：id=2的username=jon
			}
			jdbcUtil.release(cn2, st2, rs2);
			
			
			String sql3 = "update user set username = 'up王' where id = 3";
			update = statement.executeUpdate(sql3);
			System.out.println("执行成功，生效条目数："+update);
			
			//回滚操作
//			connection.rollback();
//			//用本connection来查询
//			resultSet = statement.executeQuery(sql2);
//			while(resultSet.next()) {
//				System.out.println("通过本connection查询：id=2的username="+ resultSet.getString(1));
//				//打印 通过本connection查询：id=2的username=jon
//			}
			
			//提交事务
			//connection.commit();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}
}
