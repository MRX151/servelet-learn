package com.mrx.sps;

import java.sql.ResultSet;
import java.util.List;


public interface ResultSetHandler {
	/**
	 * 传入标准ResultSet对象，返回Object对象
	 * @param resultSet
	 * @return
	 */
	public List<Object> handle(ResultSet resultSet);
}
