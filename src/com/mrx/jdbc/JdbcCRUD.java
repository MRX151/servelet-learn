package com.mrx.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JdbcCRUD {

	public JdbcUtil jdbcUtil = new JdbcUtil();
	public DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws SQLException {
		JdbcCRUD jdbcCRUD = new JdbcCRUD();
//		jdbcCRUD.create();
		// jdbcCRUD.read();
		// jdbcCRUD.update();
//		jdbcCRUD.delete();
		
		jdbcCRUD.create_prepared();
	}

	public void create() throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = jdbcUtil.getConnection();
			statement = connection.createStatement();

			// 指定主键id的生成方式
			String sql = "insert into student(id,name,birth,lesson_id,grade) values(99,'宋江','2019-10-22 00:00:00',2,'GRADE_TWO')";
			int up = statement.executeUpdate(sql);
			System.out.println("执行成功，生效行数：" + up + ",释放资源");

			// 不指定主键id的生成方式
//		String sql = "insert into student(name,birth,lesson_id,grade) values('宋江','2019-10-22 00:00:00',2,'GRADE_TWO')";
//		int id = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//		System.out.println("执行成功，生成的主键："+id+",释放资源");	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.release(connection, statement, null);
		}

	}

	public void read() throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = jdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "select * from student";
			rs = statement.executeQuery(sql);

			while (rs.next()) {// next()方法的作用是将rs向下移动一行——其初始状态是在第一行的前一行。
				System.out.println("id=" + rs.getObject("id"));
				System.out.println("name=" + rs.getObject("name"));
				System.out.println("lesson_id=" + rs.getObject("lesson_id"));
				System.out.println("grade=" + rs.getObject("grade"));
				System.out.println("birth=" + rs.getObject("birth"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.release(connection, statement, rs);
		}

	}

	public void update() throws SQLException {
		Connection connection = jdbcUtil.getConnection();
		Statement statement = connection.createStatement();

		// 指定主键id的生成方式
		String sql = "UPDATE student SET lesson_id = 99 WHERE grade = 'GRADE_THREE'";
		int up = statement.executeUpdate(sql);
		System.out.println("执行成功，生效行数：" + up + ",释放资源");

		jdbcUtil.release(connection, statement, null);
	}

	public void delete() throws SQLException {
		Connection connection = jdbcUtil.getConnection();
		Statement statement = connection.createStatement();

		// 指定主键id的生成方式
		String sql = "DELETE from student WHERE id = 99";
		int up = statement.executeUpdate(sql);
		System.out.println("执行成功，生效行数：" + up + ",释放资源");

		jdbcUtil.release(connection, statement, null);
	}

	public void create_prepared() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = jdbcUtil.getConnection();
			String sql = "insert into student(id,name,birth,lesson_id,grade) values(?,?,?,?,?)";
			// 通过语句预生成PreparedStatement
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 99);
			statement.setString(2, "宋江");
			statement.setDate(3, new java.sql.Date(formatter.parse("2019-10-22").getTime()));
			statement.setInt(4, 2);
			statement.setString(5, "GRADE_TWO");
			
			int num = statement.executeUpdate();
			System.out.println("执行成功，生效行数：" + num + ",释放资源");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.release(connection, statement, resultSet);
		}
	}
}
