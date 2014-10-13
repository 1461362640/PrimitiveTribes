package com.easyui.core.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.easyui.common.entity.Menu;
import com.easyui.common.entity.Permission;
import com.easyui.common.entity.User;
import com.easyui.web.service.MenuService;
import com.easyui.web.service.UserService;

public class TreeUtil {
	private final static String HEAD = "[";
	private final static String END = "]";
	// 子菜单格式
	private final static String SUB_HEADER = "\"children\":[";
	private final static String SUB_END = "]";

	// 返回栏目的树形json格式
	public static Object toTreeJson(MenuService menuService) {
		// 初始化栏目id
		String menuId = "0";
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(HEAD);
		sBuffer.append(getSubMenus(menuId, menuService) == null ? ""
				: getSubMenus(menuId, menuService).substring(1));
		sBuffer.append(END);
		return sBuffer.toString();
	}

	// 系统初始化菜单栏目
	public static Object startMenu(MenuService menuService) {
		// 初始化栏目id
		String menuId = "0";
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(HEAD);
		sBuffer.append(getTreeMenu(menuId, menuService) == null ? ""
				: getTreeMenu(menuId, menuService).toString().substring(1));
		sBuffer.append(END);
		return sBuffer.toString();
	}

	// 系统初始化菜单栏目
	public static Object comBoxMenu(MenuService menuService) {
		// 初始化栏目id
		String menuId = "0";
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(HEAD);
		sBuffer.append(getTreeMenu(menuId, menuService) == null ? ""
				: getTreeMenu(menuId, menuService).toString().substring(1));
		sBuffer.append(END);
		return sBuffer.toString();
	}

	// 系统权限分配中的用户数据列表
	public static Object getUserDatas(UserService userService) {
		// 初始化栏目id
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(HEAD);
		sBuffer.append(userData(userService) == null ? "" : userData(
				userService).toString().substring(1));
		sBuffer.append(END);
		return sBuffer.toString();
	}

	/**
	 * 一下方法为 各种树形结构实现方法
	 * 
	 * @param menuId
	 * @param menuService
	 * @return
	 */

	// 得到树形的结构
	@SuppressWarnings({ "unchecked" })
	public static Object getTreeMenu(String menuId, MenuService menuService) {
		StringBuffer sBuff = new StringBuffer();

		String sql = "select * from " + Hibernate.getTableName(Menu.class)
				+ " where menuid ='" + menuId + "' order by menuIndex desc";
		List<Menu> menus = (List<Menu>) menuService.getEntitysSQL(Menu.class,
				sql);
		if (menus.size() > 0) {
			for (Menu menu : menus) {
				sBuff.append(",{");
				sBuff.append("\"id\":");
				sBuff.append(menu.getRecordIndex());
				sBuff.append(",");
				sBuff.append("\"lines\":");
				sBuff.append(true);
				sBuff.append(",");
				sBuff.append("\"text\":\"");
				sBuff.append(menu.getMenuname());
				sBuff.append("\",");
				sBuff.append("\"iconCls\":\"");
				sBuff.append(menu.getIcon());
				sBuff.append("\",");
				sBuff.append("\"url\":\"");
				sBuff.append(menu.getUrl());
				sBuff.append("\",");
				sBuff.append("\"checkbox\":");
				sBuff.append(true);
				sBuff.append(",");
				sBuff.append("\"state\":");
				sBuff.append("\"open\"");
				sBuff.append(",");
				sBuff.append("\"checked\":");
				if (menu.getIsCheck() == 0) {
					sBuff.append(false);
				} else if (menu.getIsCheck() == 1) {
					sBuff.append(true);
				}
				// 判断是否有子栏目
				String subTree = getTreeMenu(menu.getRecordIndex(), menuService)
						.toString();
				if (subTree != null && !"".equalsIgnoreCase(subTree)) {
					sBuff.append(",");
					sBuff.append(SUB_HEADER).append(subTree.substring(1))
							.append(SUB_END);
				}

				sBuff.append("}");
				// 子栏目
				getTreeMenu(menu.getRecordIndex(), menuService);
			}
		}
		return sBuff.toString();
	}

	// 权限的用户列表
	public static Object userData(UserService userService) {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "select * from " + Hibernate.getTableName(User.class);
		List<User> users = (List<User>) userService.getEntitysSQL(User.class,
				sql);
		if (users.size() > 0) {
			for (User user : users) {
				sBuffer.append(",{");
				sBuffer.append("\"id\":");
				sBuffer.append(user.getId());
				sBuffer.append(",\"name\":\"");
				sBuffer.append(user.getName());
				sBuffer.append("\"}");
			}
		}
		return sBuffer.toString();
	}

	// ztree的json格式
	@SuppressWarnings({ "unchecked" })
	private static String getSubMenus(String menuId, MenuService menuService) {
		StringBuffer sBuffer = new StringBuffer();
		String mSql = "select * from " + Hibernate.getTableName(Menu.class)
				+ " where menuid ='" + menuId + "'";
		List<Menu> menus = (List<Menu>) menuService.getEntitysSQL(Menu.class,
				mSql);
		if (menus.size() > 0) {
			for (Menu menu : menus) {
				sBuffer.append(",{");
				sBuffer.append("\"id\":")
						.append(menu.getRecordIndex())
						.append(",\"parentId\":")
						.append(menu.getMenuId() == null ? "0" : menu
								.getMenuId()).append(",\"name\":\"")
						.append(menu.getMenuname()).append("\"");
				sBuffer.append("}");
				// 循环出子类
				sBuffer.append(getSubMenus(menu.getRecordIndex(), menuService));
			}
		}
		return sBuffer.toString();
	}

	// 显示栏目名称
	public static Menu dealWithMenu(List<Menu> menuList, Menu menu, int index) {
		for (Menu m1 : menuList) {
			if (m1.getRecordIndex().equalsIgnoreCase(
					(menu.getMenuId() == null ? "-9999" : menu.getMenuId()))) {
				menu.setMenuId(m1.getMenuname());
				return menu;
			}
		}
		return menu;
	}

	// 处理权限中栏目数组
	public static String dMenu(List<Permission> permissions) {
		Set<String> set = new HashSet<String>();
		if (permissions != null && permissions.size() > 0) {
			for (Permission per : permissions) {
				String[] ids = per.getMenuIds().split(",");
				if (ids != null && ids.length > 0) {
					for (String id : ids) {
						set.add(id);
					}
				}

			}
			return set.toString();
		}
		return null;
	}
	
	// 处理权限中栏目数组
		public static String dUser(List<Permission> permissions) {
			Set<String> set = new HashSet<String>();
			if (permissions != null && permissions.size() > 0) {
				for (Permission per : permissions) {
					String[] ids = per.getUserIds().split(",");
					if (ids != null && ids.length > 0) {
						for (String id : ids) {
							set.add(id);
						}
					}

				}
				return set.toString();
			}
			return null;
		}

}
