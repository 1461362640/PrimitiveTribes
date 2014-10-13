package com.easyui.common.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 栏目表
 * 
 * @author Chenyz
 * @param <T>
 */
@Entity
@Table(name = "t_menu")
public class Menu {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.AUTO) */
	@Column(name = "id", nullable = true, length = 32)
	private String id;
	@Column(name = "menuname", length = 32)
	private String menuname;// 栏目名称
	@Column(name = "recordIndex", length = 32)
	private String recordIndex;// 记录索引
	@Column(name = "menuId", length = 32)
	private String menuId;// 父栏目id
	@Column(name = "subId", length = 32)
	private String subId;// 子栏目id
	@Column(name = "icon", length = 20)
	private String icon;// 栏目图标
	@Column(name = "url", length = 20)
	private String url;// 栏目链接
	@Column(name = "menuIndex", length = 2)
	private short menuIndex;// 栏目顺序
	@Column(name = "isCheck", length = 2)
	private short isCheck;// 栏目顺序
	@Column(name = "isOpen", length = 2)
	private short isOpen;// 栏目顺序

	private String state;// 栏目状态

	private Date commitTime;

	public Menu() {
		super();
	}

	public Menu(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public short getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(short menuIndex) {
		this.menuIndex = menuIndex;
	}

	public short getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(short isCheck) {
		this.isCheck = isCheck;
	}

	public short getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(short isOpen) {
		this.isOpen = isOpen;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public String getRecordIndex() {
		return recordIndex;
	}

	public void setRecordIndex(String recordIndex) {
		this.recordIndex = recordIndex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuname=" + menuname + ", recordIndex="
				+ recordIndex + ", menuId=" + menuId + ", subId=" + subId
				+ ", icon=" + icon + ", url=" + url + ", menuIndex="
				+ menuIndex + ", isCheck=" + isCheck + ", isOpen=" + isOpen
				+ ", state=" + state + ", commitTime=" + commitTime + "]\n";
	}

}
