package com.gouxiang.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gouxiang.common.entity.Log;
import com.gouxiang.common.entity.Menu;
import com.gouxiang.common.entity.Permission;
import com.gouxiang.common.entity.User;
import com.gouxiang.core.dao.BaseDao;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.Hibernate;
import com.gouxiang.core.util.IPAddress;
import com.gouxiang.core.util.MenuUtil;
import com.gouxiang.core.util.TreeUtil;
import com.gouxiang.core.util.UUIDTool;
import com.gouxiang.web.service.MenuService;
import com.gouxiang.web.service.UserService;
import com.gouxiang.web.sql.SQL;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public User queryByNP(String userName, String passWord, Class<User> clazz) {
		List<?> list = baseDao.queryByNP(userName, passWord, clazz);
		if (list != null && list.size() == 1) {
			User user = (User) list.get(0);
			if (user.getUsername().equalsIgnoreCase(userName)
					&& user.getPassword().equalsIgnoreCase(passWord)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<?> findAll(Class<User> clazz) {
		return baseDao.findAll(clazz);
	}

	@Override
	public String getRecordIndex(Class<User> clazz) {
		String sql = "select * from " + Hibernate.getTableName(clazz)
				+ " where recordIndex=(select max(recordIndex) from "
				+ Hibernate.getTableName(clazz) + ")";
		List<?> list = baseDao.getEntitysHQL(clazz, sql);
		if (!list.isEmpty()) {
			Menu menu = (Menu) list.get(0);
			return menu.getRecordIndex();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getEntitysSQL(Class<User> clazz, String sql) {
		return (List<User>) baseDao.getEntitysSQL(clazz, sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reloadPermission(HttpServletRequest request,
			SuperService superService, MenuService menuService) {
		String userIndex = "0";
		Object userObj = request.getSession().getAttribute("userIndex");
		if (userObj != null) {
			userIndex = (String) userObj;
		}
		// 读取当前用户栏目权限
		List<Permission> permissions = (List<Permission>) superService
				.getEntitysSQL(Permission.class,
						SQL.LOGINACTION_PERMISSION_P.value(userIndex));
		String menuIds = TreeUtil.dMenu(permissions);
		if (menuIds != null && !"".equalsIgnoreCase(menuIds)) {
			// 左侧栏目菜单
			new MenuUtil().putMenu(request, menuService, userIndex, menuIds
					.replace("[", "").replace("]", ""));
		}
	}

	@Override
	public void recordLog(HttpServletRequest request, String indexTip) {
		Log log=new Log();
		String ip=IPAddress.getIpAddrs(request);
		log.setId(UUIDTool.getIntId());
		log.setIp(ip);
		Object obj=request.getSession().getAttribute("user");
		log.setUsername(obj==null?"":obj.toString());
		log.setLoginTime(new Date(System.currentTimeMillis()));
		log.setOperation(indexTip.split(",")[1].split(":")[1].replace("!", "").replace("\"","").replace("\"", "").replace("}", ""));
		baseDao.save(log);
	}

	

}
