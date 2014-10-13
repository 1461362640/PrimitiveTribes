package com.easyui.core.util;

import java.util.Properties;

import com.easyui.core.exception.CustomException;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			Propertie
 * Date:			2014-6-29
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		配置文件加载工具
 * </pre>
 **/
public class Propertie {
	Properties properties = new Properties();
		public static Properties getPro() {
			Properties properties = new Properties();
			try {
				properties.load(Propertie.class.getClassLoader()
						.getResourceAsStream("dev_info.properties"));
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
