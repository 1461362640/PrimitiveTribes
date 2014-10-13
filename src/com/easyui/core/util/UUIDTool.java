package com.easyui.core.util;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Filename:		com.xilei.edu.core.util.UUIDSupport.java
 * Class:			UUIDSupport
 * Date:			2014-6-26
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		利用UUID主键自动生成策略
 * </pre>
 **/
@MappedSuperclass
public class UUIDTool implements Serializable {

	private static final long serialVersionUID = 1263752814740486595L;
	@Id
	@Column(length = 32, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected String id;

	public UUIDTool(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId() {
		String str = UUID.randomUUID().toString();
		String dat = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		this.id = dat;
	}

	public UUIDTool() {
		String str = UUID.randomUUID().toString();
		String dat = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		this.id = dat;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UUIDTool)) {
			return false;
		}
		UUIDTool uuid = (UUIDTool) obj;
		return getId().equals(uuid.getId());
	}

	/**
	 * 符合模块的格�?
	 * 
	 * @return
	 */
	public static String getIntId() {
		//Random random = new Random();
		return new UUIDTool().getId();
	}
	
	
	//id转化
	public static String convIdToStr(String params){
		String ids[];
		String str="";
		if(params!=null&&!"".equalsIgnoreCase(params)){
			ids=params.split(",");
			for(String id:ids){
				str+=",'"+id+"'";
			}
			return str.substring(1);
		}
		return null;
	}

}
