package com.gouxiang.common.entity;

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
 * Description:		ӰƬ����
 * </pre>
 **/
@Entity
@Table(name = "t_filmtype")
public class FilmType {
	@Id
	@Basic(optional = false)
	/* @GeneratedValue(strategy = GenerationType.IDENTITY) */
	@Column(name = "id", nullable = false, length = 32)
	private String id;
	@Column(name = "text",  length = 200)
	private String text;// ϲ����
	@Column(name = "selected", nullable = true, length = 2)
	private boolean selected;// �Ƿ�ѡ��
	@Column(name = "descs",  length = 255)
	private String descs;// ����

	public FilmType() {
		super();
	}

	public FilmType(String id, String text, boolean selected, String descs) {
		super();
		this.id = id;
		this.text = text;
		this.selected = selected;
		this.descs = descs;
	}
	
	public FilmType(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	@Override
	public String toString() {
		return "FilmType [id=" + id + ", text=" + text + ", selected="
				+ selected + ", descs=" + descs + "]";
	}

}
