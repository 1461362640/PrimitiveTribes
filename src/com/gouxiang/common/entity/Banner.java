package com.gouxiang.common.entity;

import java.io.Serializable;
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
 * Description:		横幅
 * </pre>
 **/

@SuppressWarnings("serial")
@Entity
@Table(name = "t_banner")
public class Banner implements Serializable {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.IDENTITY) */
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	private String name;// 横幅名称
	private String videoUrl;// 横幅跳转地址
	private String imageUrl;// 横幅地址
	private Date uploadDate;// 图片上传时间
	private Date editDate;// 编辑时间
	private int status;// 状态

	public Banner() {
		super();
	}

	public Banner(String id) {
		super();
		this.id = id;
	}

	public Banner(String id, String name, String videoUrl, String imageUrl,
			Date uploadDate, Date editDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.videoUrl = videoUrl;
		this.imageUrl = imageUrl;
		this.uploadDate = uploadDate;
		this.editDate = editDate;
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}