package com.gouxiang.core.util;

import javax.persistence.Table;

/**
 * ͨ��ע��javax.persistence.Table��ȡ��ݿ��ľ�����Ϣ java hibernate ��� Table ע�� ��ȡ ��ݿ� ����
 * �ֶ��� ������ ��Ҫ ע�ⷽʽΪ javax.persistence.Table��ע��
 * 
 * @author Chenyz
 * 
 */
public class Hibernate {

	/**
	 * ��ñ���
	 * 
	 * @param clazz
	 *            ӳ�䵽��ݿ��po��
	 * @return String
	 */
	public static String getTableName(Class<?> clazz) {
		Table annotation = clazz.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return null;
	}

}
