package com.easyui.core.util;

import javax.persistence.Table;

/**
 * 通过注解javax.persistence.Table获取数据库表的具体信息 java hibernate 根据 Table 注解 获取 数据库 表名
 * 字段名 工具类 需要 注解方式为 javax.persistence.Table的注解
 * 
 * @author Chenyz
 * 
 */
public class Hibernate {

	/**
	 * 获得表名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @return String
	 */
	public static String getTableName(Class<?> clazz) {
		Table annotation = clazz.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return null;
	}

}
