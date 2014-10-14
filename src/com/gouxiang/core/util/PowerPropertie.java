package com.gouxiang.core.util;

import java.util.Properties;

import com.gouxiang.core.exception.CustomException;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			PowerPropertie
 * Date:			2014-8-23
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		配置文件加载工具
 * </pre>
 **/
public class PowerPropertie {
	Properties properties = new Properties();

	public static Properties getPro() {
		Properties properties = new Properties();
		try {
			properties.load(Propertie.class.getClassLoader()
					.getResourceAsStream("fire.properties"));
		} catch (Exception e) {
			new CustomException(e);
		}
		return properties;
	}

	/**
	 * 根据key返回对应的value
	 * 
	 * @param key
	 *            帶查找的key
	 * @return
	 */
	public static String getVal(String key) {
		return getPro().getProperty(key);
	}
}
