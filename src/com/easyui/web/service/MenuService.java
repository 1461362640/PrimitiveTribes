package com.easyui.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.easyui.common.entity.Menu;
@Service
public interface MenuService {

	boolean saveOrUpdate(Menu menu);

	boolean save(Menu menu);

	List<?> findAll(Class<Menu> clazz,String sort,String order, int page, int rows);

	String getRecordIndex(Class<Menu> clazz);

	List<?> getEntitysSQL(Class<?> clazz, String sql);

	List<Menu> getTreeNode(String hql, Map<String, Object> map);

}
