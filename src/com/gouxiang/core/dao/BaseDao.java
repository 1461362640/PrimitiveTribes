package com.gouxiang.core.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, www.hzxilei.com
 * Filename:		com.easyui.web.dao.BaseDao.java
 * Class:			BaseDao
 * Date:			2013-12-13
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		基础dao成的封装
 * </pre>
 **/

public interface BaseDao {
	 
	/** 实体的添加 **/
	public <T> boolean addEntity(T entity);
	/**查询对象列表**/
	public List<?> findAll(Class<?> clazz);
	/**登陆查询**/
	public List<?>  queryByNP(String userName, String passWord, Class<?> clazz);
	/**总记录统计**/
	public BigInteger getCount(Class<?> clazz);
	/**总记录统计[有分页]**/
	public List<?> findAll(Class<?> clazz, int page, int rows,String sort,String order,BigInteger total);
	/**新增或更新
	 * @param <T>**/
	public <T> boolean saveOrUpdate(Object obj);
	/**根据id获取对象**/
	public Object getEntity(Class<?> clazz, String id);
	/**hql查询**/
	public List<?> getEntitysHQL(Class<?> clazz,String hql);
	/**单个实体的删除操作**/
	public boolean delEntity(Object obj);
	/**保存操作
	 * @param <T>**/
	public <T> boolean save(Object clazz);
	public boolean delete(Class<?> clazz, String id);
	/**原声sql支持**/
	public List<?> getEntitysSQL(Class<?> clazz, String sql);
	/**栏目查询**/
	public List<?> getTreeNode(String hql, Map<String, Object> map);
}
