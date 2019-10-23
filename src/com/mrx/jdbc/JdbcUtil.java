package com.mrx.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.mrx.entity.Student;
import com.mrx.entity.User;
import com.mrx.sps.BeanListHandler;
import com.mrx.sps.ResultSetHandler;

public class JdbcUtil {

	private static JdbcDataSource dataSource = new JdbcDataSource();

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	// 静态初始化代码块,会在类被第一次加载时执行
	static {
		try {
			// 通过InputStream来读取properties文件
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);// 通过文件输入流，实例化properties

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			// 加载驱动
			Class.forName(driver);

		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 基础方式获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public static Connection getConnectionFromDataSource() throws SQLException {
		return dataSource.getConnection();
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				// 关闭存储查询结果的ResultSet对象
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				// 关闭负责执行SQL命令的Statement对象
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				// 关闭Connection数据库连接对象
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int update(String sql, String[] params) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int update = -1;

		try {
			connection = JdbcUtil.getConnectionFromDataSource();
			statement = connection.prepareStatement(sql);
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}

			update = statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(connection, statement, resultSet);
		}

		return update;
	}

	public static List<Object> query(String sql, String[] params, ResultSetHandler handler) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Object> results = null;

		try {
			connection = JdbcUtil.getConnectionFromDataSource();
			statement = connection.prepareStatement(sql);
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}

			resultSet = statement.executeQuery();
			return handler.handle(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(connection, statement, resultSet);
		}

		return results;
	}

//	public static int delete(String sql, String[] params) {
//		int delete = -1;
//		Connection connection = null;
//		PreparedStatement statement = null;
//
//		try {
//			connection = JdbcUtil.getConnectionFromDataSource();
//			statement = connection.prepareStatement(sql);
//			if (null != params && 0 != params.length) {
//				for (int i = 0; i < params.length; i++) {
//					statement.setObject(i + 1, params[i]);
//				}
//			}
//			delete = statement.ex
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			JdbcUtil.release(connection, statement, null);
//		}
//
//		return delete;
//	}

	public static void main(String[] args) {
		// 测试简单插入CREATE
		String basic_insert = "insert into user(username,password)values(?,?)";
		String[] basic_insert_params = { "熊up", "jon123" };
//		JdbcUtil.update(basic_insert, basic_insert_params);

		// 测试更新UPDATE
		String basic_update = "update user set username = ? where id = ?";
		String[] basic_update_params = { "小燕子", "16" };
//		JdbcUtil.update(basic_update, basic_update_params);

		// 测试复杂插入CREATE
		String insert = "insert into student(name,birth,lesson_id,grade)values(?,?,?,?)";
		// String可以空着，但数字类型的数据不能空着
		String[] insert_params = { "", "1990-7-11 00:00:00", "3", "GRADE_TWO" };
//		JdbcUtil.update(insert, insert_params);

		// 测试简单查询
		String basic_query = "select * from user";
		String[] basic_query_params = null;
		List<Object> list = JdbcUtil.query(basic_query, basic_query_params, new BeanListHandler(User.class));
		System.out.println("简单查询结束");

		// 测试复杂查询
		String query = "select * from student";
		List<Object> query_list = JdbcUtil.query(query, null, new BeanListHandler(Student.class));
		Date date = ((Student) query_list.get(0)).getBirth();
		System.out.println("复杂查询结束,打印第一条结果的Date数据：" + date.toString());

		// 测试删除
		String delete = "delete from user where id = ?";
		String[] delete_params = { "17" };
		JdbcUtil.update(delete, delete_params);
	}
}
