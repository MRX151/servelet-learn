package com.mrx.jdbc;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JdbcDataSource implements DataSource {

	// 因为需要频繁读写，推荐用LinkedList
	private static LinkedList<Connection> connections = new LinkedList<>();
	// 初始化静态代码块
	static {
		InputStream in = JdbcDataSource.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			System.out.print("数据池初始化开始...");
			prop.load(in);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
			// 载入driver
			Class.forName(driver);
			// 初始化jdbcPoolInitSize数量的connection
			for (int i = 0; i < jdbcPoolInitSize; i++) {
				Connection cnn = DriverManager.getConnection(url, username, password);

				connections.add(cnn);
			}
			System.out.println("数据池初始化完毕，初始化连接数:" + jdbcPoolInitSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcDataSource dataSource = new JdbcDataSource();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// 如果数据库连接池中的连接对象的个数大于0
		if (connections.size() > 0) {
			// 从listConnections集合中获取一个数据库连接
			final Connection conn = connections.removeFirst();
			System.out.println("正在请求连接，connection数据库连接池大小是" + connections.size());
			// 返回Connection对象的代理对象,这样，connection在别处被调用时，完全不需要修改任何逻辑。
			// 这里的类加载器的选择是灵活的，一般使用被代理类的类加载器。
			return (Connection) Proxy.newProxyInstance(JdbcDataSource.class.getClassLoader(),
					conn.getClass().getInterfaces(), (proxy, method, args) -> {
						if (!method.getName().equals("close")) {
							return method.invoke(conn, args);
						} else {
							// 如果调用的是Connection对象的close方法，就把conn还给数据库连接池
							connections.add(conn);
							System.out.print(conn + "被还给connection数据库连接池了！！");
							System.out.println("connection数据库连接池大小为" + connections.size());
							return null;
						}
					});
		} else {
			throw new RuntimeException("对不起，数据库忙");
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
