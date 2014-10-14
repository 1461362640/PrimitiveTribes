package com.gouxiang.common.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Date:			2014-6-26
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		软件版本控制
 * </pre>
 **/
@Entity
@Table(name = "version")
public class Version {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.IDENTITY) */
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	private String name;// ��Ƶ���
	private String url;
	private String version;// �汾��
	private String describes;// ����
	private Date uploadDate;// �ϴ�ʱ��
	private int status;// ״̬

	public Version() {
		super();
	}

	public Version(String id, String name, String url, String version,
			String describes, Date uploadDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.version = version;
		this.describes = describes;
		this.uploadDate = uploadDate;
		this.status = status;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
