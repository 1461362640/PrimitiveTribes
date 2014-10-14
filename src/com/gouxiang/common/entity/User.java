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
 * Copyright:		Copyright(C) 2012-2013
 * Date:			2014-1-10
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		用户实体
 * </pre>
 **/

@SuppressWarnings("serial")
@Entity
@Table(name = "t_user")
public class User implements Serializable {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.AUTO) */
	@Column(name = "id", nullable = true, length = 32)
	private String id;
	@Column(name = "username", length = 32)
	private String username; // 帐名
	@Column(name = "password", length = 32)
	private String password; // 密码
	@Column(name = "type", length = 2)
	private int type; // 用户类型 1.管理员 2.游客
	@Column(name = "name", length = 32)
	private String name; // 真名
	@Column(name = "sex", length = 2)
	private int sex; // 性别 1-男性 2-女性
	@Column(name = "email", length = 32)
	private String email; // 电子邮件
	@Column(name = "image", length = 32)
	private String image; // 头像
	@Column(name = "nickname", length = 32)
	private String nickname; // 昵称
	@Column(name = "mobilephone", length = 11)
	private String mobilephone; // 手机号码
	@Column(name = "recordIndex", length = 32)
	private String recordIndex;// 记录索引

	private Date registerDate;// 注册时间
	private Date modifyDate;// 修改时间

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public User(String username, String password, int type, String name,
			int sex, String email, String image, String nickname,
			String mobilephone) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.image = image;
		this.nickname = nickname;
		this.mobilephone = mobilephone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Override
	public String toString() {
		return "<id:" + this.id + ",姓名:" + this.name + ",密码:" + this.password
				+ ">";
	}

	public String getRecordIndex() {
		return recordIndex;
	}

	public void setRecordIndex(String recordIndex) {
		this.recordIndex = recordIndex;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
