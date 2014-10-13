package com.easyui.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013
 * Date:			2014-1-10
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		权限组
 * </pre>
 **/

@SuppressWarnings("serial")
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.AUTO) */
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	@Column(name = "name", length = 32)
	private String name;// 权限组名称
	@Column(name = "userIds", length = 255)
	private String userIds;// 用户组
	@Column(name = "menuIds", length = 255)
	private String menuIds;// 用户组

	public Permission() {
		super();
	}

	public Permission(String id) {
		super();
		this.id = id;
	}
	public Permission(String userIds, String menuIds) {
		super();
		this.userIds = userIds;
		this.menuIds = menuIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

}
