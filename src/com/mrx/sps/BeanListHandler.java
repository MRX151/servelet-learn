package com.mrx.sps;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler implements ResultSetHandler {

	private Class<?> clazz;

	public BeanListHandler(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<Object> handle(ResultSet resultSet) {
		try {
			List<Object> list = new ArrayList<>();

			while (resultSet.next()) {
				Object bean = clazz.newInstance();
				ResultSetMetaData metaData = resultSet.getMetaData();
				int count = metaData.getColumnCount();
				for (int i = 0; i < count; i++) {
					String name = metaData.getColumnName(i + 1);
					Object value = resultSet.getObject(i + 1);
					if (null != value) {
						// 这里的Field，即使设为私有，也可以获取到
						Field f = bean.getClass().getDeclaredField(name);
						f.setAccessible(true);
						f.set(bean, value);
					}

				}
				list.add(bean);
			}
			return list.size() > 0 ? list : null;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
