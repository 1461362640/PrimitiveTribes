package com.gouxiang.core.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gouxiang.core.dao.BaseDao;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.Hibernate;
@Transactional
@Service("superService")
public class SuperServiceImpl implements SuperService {

	@Autowired
	private BaseDao baseDao;

	public List<?> findAll(Class<?> clazz) throws Exception {
		List<?> list = baseDao.findAll(clazz);
		return list;
	}

	@Override
	public <T> void save(T entity) {
		this.baseDao.addEntity(entity);
	}

	@Override
	public List<?> queryByNP(String userName, String passWord, Class<?> clazz) {
		return this.baseDao.queryByNP(userName, passWord, clazz);
	}

	@Override
	public BigInteger getCount(Class<?> clazz) {
		return this.baseDao.getCount(clazz);
	}
	
	public List<?> findAll(Class<?> clazz, String page, String rows) throws Exception {
		int iPage=0;
		int iRows=0;
		if(page!=null&&!"".equalsIgnoreCase(page)){
			iPage=Integer.parseInt(page);
		}
		if(rows!=null&&!"".equalsIgnoreCase(rows)){
			iRows=Integer.parseInt(rows);
		}
		return this.baseDao.findAll(clazz, iPage, iRows,null,null,null);
	}

	@Override
	public List<?> findAll(Class<?> clazz, String page, String rows,String sort,String order,BigInteger total) throws Exception {
		int iPage=0;
		int iRows=0;
		if(page!=null&&!"".equalsIgnoreCase(page)){
			iPage=Integer.parseInt(page);
		}
		if(rows!=null&&!"".equalsIgnoreCase(rows)){
			iRows=Integer.parseInt(rows);
		}
		return this.baseDao.findAll(clazz, iPage, iRows,sort,order,total);
	}

	public <T> boolean saveOrUpdate(Object obj) {
		return this.baseDao.saveOrUpdate(obj);
	}

	@Override
	public <T> Object getEnity(Class<?> clazz, String id) {
		return this.baseDao.getEntity(clazz, id);
	}

	@Override
	public List<?> getEntitysHQL(Class<?> clazz,String hql) {
		return this.baseDao.getEntitysHQL(clazz,hql);
	}

	@Override
	public boolean delEntity(Object obj) {
		return this.baseDao.delEntity(obj);
	}

	@Override
	public boolean delete(Class<?> clazz, String id) {
		return this.baseDao.delete(clazz,id);
	}


	@Override
	public Object getRecordIndex(Class<?> clazz) {
		String sql = "select * from " + Hibernate.getTableName(clazz)
				+ " where recordIndex=(select max(recordIndex) from "
				+ Hibernate.getTableName(clazz) + ")";
		List<?> list = baseDao.getEntitysSQL(clazz, sql);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return 0;
	}

	@Override
	public List<?> getEntitysSQL(Class<?> clazz, String sql) {
		return baseDao.getEntitysSQL(clazz, sql);
	}

}
