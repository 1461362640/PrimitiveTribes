package com.gouxiang.core.util;

public class ID {
	
	public static boolean isNull(String id){
		if(id!=null&&!"".equalsIgnoreCase(id.trim())){
			return false;
		}
		return true;
	}
	//返回去重后的id
	public static String id_Split(String id){
		if(id!=null&&!"".equalsIgnoreCase(id)){
			return id.split(",")[0];
		}
		return null;
	}
}
