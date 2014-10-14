package com.gouxiang.core.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, www.hzxilei.com
 * Filename:		com.xilei.edu.web.service.SuperService.java
 * Class:			SuperService
 * Date:			2013-12-13
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		基础service的封装
 * </pre>
 **/
@Service
public interface SuperService {
/**查询所有无分页**/
	List<?> findAll(Class<?> clazz) throws Exception;
	/**查询所有有分页**/
	List<?> findAll(Class<?> clazz, String page, String rows,String sort,String order,BigInteger total) throws Exception;
	/**查询所有有分页**/
	List<?> findAll(Class<?> clazz, String page, String rows) throws Exception;

	<T> void save(T entity);
	/**
	 * 登陆查询
	 * 
	 * @return
	 **/
	List<?> queryByNP(String userName, String passWord, Class<?> clazz);
	/**总记录统计**/
	BigInteger getCount(Class<?> clazz);
	
	<T> boolean saveOrUpdate(Object obj);
	
	<T> Object getEnity(Class<?> clazz,String id);
	/**hql查询**/
	List<?> getEntitysHQL(Class<?> clazz,String hql);
	/**sql查询**/
	List<?> getEntitysSQL(Class<?> clazz, String hql);
	/**单个实体的删除操作**/
	boolean delEntity(Object obj);
	
	boolean delete(Class<?> clazz, String id);
	//取得索引的最大值
	Object getRecordIndex(Class<?> clazz);
	

}
