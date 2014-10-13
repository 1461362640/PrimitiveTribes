package com.easyui.web.sql;

import com.easyui.common.entity.Permission;
import com.easyui.core.util.Hibernate;

/**
 * sql语句的提取
 * 
 * @author Chenyz
 *
 */
public enum SQL {
	LOGINACTION_PERMISSION_P;
	
	public String value(String args){
		switch(this){
		case LOGINACTION_PERMISSION_P:
			return "select * from "+Hibernate.getTableName(Permission.class)+" where userIds like '%"+args+"%'";
			default:
				return "ERROR";
		}
	}
	
	 
}
