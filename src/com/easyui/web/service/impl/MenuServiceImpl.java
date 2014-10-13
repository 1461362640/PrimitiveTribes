package com.easyui.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyui.common.entity.Menu;
import com.easyui.core.dao.BaseDao;
import com.easyui.core.util.Hibernate;
import com.easyui.web.service.MenuService;
@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public boolean saveOrUpdate(Menu menu) {
		return baseDao.saveOrUpdate(menu);
	}

	@Override
	public boolean save(Menu menu) {
		return baseDao.save(menu);
	}

	@Override
	public List<?> findAll(Class<Menu> clazz,String sort,String order, int page, int rows) {
		int minSize = 0;
		int maxSize = 0;
		if (page == 1 || page == 0) {
			page = 0;
			maxSize = 1 * rows;
			minSize = page;
		} else {
			maxSize = page * rows;
			minSize = (page * rows) - rows;
		}
		String hql = "order by "+sort+" order "+"limit " + minSize + "," + maxSize;
		return baseDao.getEntitysHQL(clazz, hql);
	}

	@Override
	public String getRecordIndex(Class<Menu> clazz) {
		String sql = "select * from " + Hibernate.getTableName(clazz)
				+ " where recordIndex=(select max(recordIndex) from "
				+ Hibernate.getTableName(clazz) + ")";
		List<?> list = baseDao.getEntitysSQL(clazz, sql);
		if (!list.isEmpty()) {
			Menu menu = (Menu) list.get(0);
			return menu.getRecordIndex()+"";
		}
		return null;
	}

	@Override
	public List<?> getEntitysSQL(Class<?> clazz, String sql) {
		return baseDao.getEntitysSQL(clazz, sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getTreeNode(String hql, Map<String, Object> map) {
		return (List<Menu>) baseDao.getTreeNode( hql, map);
	}

}
