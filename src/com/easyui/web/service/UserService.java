package com.easyui.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.easyui.common.entity.User;
import com.easyui.core.service.SuperService;
@Service
public interface UserService {

	User queryByNP(String userName, String passWord, Class<User> class1);

	List<?> findAll(Class<User> clazz);

	String getRecordIndex(Class<User> clazz);

	List<User> getEntitysSQL(Class<User> clazz, String sql);

	void reloadPermission(HttpServletRequest request, SuperService superService, MenuService menuService);
	//记录登录日志
	void recordLog(HttpServletRequest request, String indexTip);

}
