package com.gouxiang.core.util;

import java.math.BigInteger;


/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			ToJSON
 * Date:			2014-8-18
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		返回json格式数据列表
 * </pre>
 **/
public class ToJSON {

	// 拼接easyui支持的json格式
	public static Object toJSON(Object obj, BigInteger total) {
		return "{\"total\":" + total + ",\"rows\":" + obj + "}";
	}

}
