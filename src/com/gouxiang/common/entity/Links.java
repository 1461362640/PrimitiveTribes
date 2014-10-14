package com.gouxiang.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Date:			2014-8-31
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:	        友情链接
 * </pre>
 **/
@Entity
@Table(name = "t_links")
public class Links {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.IDENTITY) */
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	@Column(name = "title", length = 32)
	private String title;//标题
	@Column(name = "href", length = 32)
	private String href;//链接地址
	@Column(name = "bold", length = 1)
	private boolean bold;//是否是粗体
	
	public Links() {
		super();
	}

	public Links(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}
 
}
