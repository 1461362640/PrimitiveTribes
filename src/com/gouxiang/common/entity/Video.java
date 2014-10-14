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
 * Description:		视频
 * </pre>
 **/

@SuppressWarnings("serial")
@Entity
@Table(name = "t_video")
public class Video implements Serializable {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.IDENTITY) */
	@Column(name = "id", nullable = false,length=32)
	private String id;
	private String name;//视频名称
	private String relName;
	private String imageUrl;//当前栏目id
	private String videoUrl; // 父栏目id
	private String type;//视频类型
	private String describes;//描述
	private Date uploadDate;//视频上传时间
	private Date editDate;//编辑时间
	private int status;//状态
	
	public Video() {
		super();
	}

	public Video(String id, String name, String relName, String imageUrl,
			String videoUrl, String type, String describes, Date uploadDate,
			Date editDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.relName = relName;
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
		this.type = type;
		this.describes = describes;
		this.uploadDate = uploadDate;
		this.editDate = editDate;
		this.status = status;
	}
	
	public Video(String id) {
		super();
		this.id = id;
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

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", relName=" + relName
				+ ", imageUrl=" + imageUrl + ", videoUrl=" + videoUrl
				+ ", type=" + type + ", describes=" + describes
				+ ", uploadDate=" + uploadDate + ", editDate=" + editDate
				+ ", status=" + status + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getRelName()=" + getRelName()
				+ ", getImageUrl()=" + getImageUrl() + ", getVideoUrl()="
				+ getVideoUrl() + ", getType()=" + getType()
				+ ", getDescribes()=" + getDescribes() + ", getUploadDate()="
				+ getUploadDate() + ", getEditDate()=" + getEditDate()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	  

}