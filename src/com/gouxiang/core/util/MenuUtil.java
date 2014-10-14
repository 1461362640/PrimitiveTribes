package com.gouxiang.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gouxiang.common.entity.Menu;
import com.gouxiang.web.service.MenuService;

public class MenuUtil {
	private final static String HEAD = "[";
	private final static String END = "]";
	// 子菜单格式
	private final static String SUB_HEADER = "\"children\":[";
	private final static String SUB_END = "]";

	private String menuIds;

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public Object startMenu(MenuService menuService) {
		// 初始化栏目id
		String menuId = "0";
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(HEAD);
		Object treeStr = getTreeMenu(menuId, menuService);
		sBuffer.append((treeStr == null) ? "" : treeStr.toString().substring(1));
		sBuffer.append(END);
		return sBuffer.toString();
	}

	// 得到树形的结构
	@SuppressWarnings({ "unchecked" })
	public String getTreeMenu(String menuId, MenuService menuService) {
		StringBuffer sBuff = new StringBuffer();

		String sql = "select * from " + Hibernate.getTableName(Menu.class)
				+ " where menuid ='" + menuId + "' order by menuIndex desc";
		List<Menu> menus = (List<Menu>) menuService.getEntitysSQL(Menu.class,
				sql);
		if (menus.size() > 0) {
			for (Menu menu : menus) {
				System.out.println(">>>["+menus.indexOf(menu)+"]getMenuIds :"+getMenuIds()+"   getRecordIndex:"+menu.getRecordIndex()+"  包含： "+getMenuIds().contains(menu.getRecordIndex()));
				if (getMenuIds().contains(menu.getRecordIndex())) {
					sBuff.append(toString(menu, menuService));
					// 子栏目
					getTreeMenu(menu.getRecordIndex(), menuService);
				}  

			}
		}
		return sBuff != null && !"".equalsIgnoreCase(sBuff.toString()) ? sBuff
				.toString() : null;
	}

	public String toString(Menu menu, MenuService menuService) {
		StringBuffer sBuff = new StringBuffer();
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
		sBuff.append("\"attributes\":{");
		sBuff.append("\"href\":\"");
		sBuff.append(menu.getUrl());
		sBuff.append("\"},");
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
		String subTree = getTreeMenu(menu.getRecordIndex(), menuService);
		if (subTree != null && !"".equalsIgnoreCase(subTree)) {
			sBuff.append(",");
			sBuff.append(SUB_HEADER).append(subTree.substring(1))
					.append(SUB_END);
		}

		sBuff.append("}");

		return sBuff.toString();
	}

	// ////////////////////////////////////////////////////////////////////////
	public List<String> sortMenuId(String ids) {
		List<String> list = new ArrayList<String>();
		char[] chars = ids.toCharArray();
		Arrays.sort(chars);
		for (char c : chars) {
			if (String.valueOf(c) != null
					&& !"".equalsIgnoreCase(String.valueOf(c))) {
				list.add(String.valueOf(c));
			}
		}
		return list;
	}

	// 栏目数组排序

	public void putMenu(HttpServletRequest request, MenuService menuService,
			String userIndex, String menuIds) {
		// 查出有权限的栏目
		/*
		 * String menuSql = "select * from " +
		 * Hibernate.getTableName(Menu.class) + " where recordIndex in(" +
		 * menuIds + ")"; List<Menu> menus = (List<Menu>)
		 * menuService.getEntitysSQL(Menu.class, menuSql);
		 */
		setMenuIds(menuIds);
		Session.setSelfParms(request, userIndex + "_Menu",
				startMenu(menuService));
	}

}
