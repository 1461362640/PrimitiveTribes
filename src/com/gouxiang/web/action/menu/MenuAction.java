package com.gouxiang.web.action.menu;

import httl.Engine;
import httl.Template;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gouxiang.common.entity.Menu;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.ID;
import com.gouxiang.core.util.Session;
import com.gouxiang.core.util.TipEnum;
import com.gouxiang.core.util.ToJSON;
import com.gouxiang.core.util.TreeUtil;
import com.gouxiang.core.util.UUIDTool;
import com.gouxiang.web.service.MenuService;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			BannerAction.java
 * Date:			2014-6-29
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		菜单列表
 * </pre>
 **/
@Controller
@RequestMapping("/menu.do")
public class MenuAction {
	@Autowired
	private SuperService superService;
	@Autowired
	private MenuService menuService;
	
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public MenuAction() {
		super();
	}
	
	public MenuAction(MenuService menuService) {
		super();
		this.menuService = menuService;
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void menuList(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/menu/menu.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	/** json方式获取数据列表 **/
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(params = "menuList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort=request.getParameter("sort");
		String order=request.getParameter("order");
		try {
			BigInteger total=superService.getCount(Menu.class);
			List<Menu> menuList = (List<Menu>) superService.findAll(Menu.class,page, rows,sort,order,total);
			List<Menu> ms = new ArrayList<Menu>();
			for (Menu m : menuList) {
				ms.add(TreeUtil.dealWithMenu(menuList, m, menuList.indexOf(m)));
			}
			return ToJSON.toJSON(JSON.toJSONString(ms),total);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveMenu")
	public @ResponseBody
	Object saveMenu(@RequestParam("id") String id,
			@RequestParam("url") String url, Menu menu) {
		try {
			menu.setState("1");
			if (!ID.isNull(id)) {
				menu.setId(ID.id_Split(id));
				menu.setCommitTime(new Date(System.currentTimeMillis()));
				menuService.saveOrUpdate(menu);
			} else {
				// 检测栏目链接是否重复
				// String hql = "where url='" + url + "'";
				// List<?> list = superService.getEntitysHQL(Menu.class, hql);
				// if (list!=null&&list.size()>0){
				// return TipEnum.CustomTip.customTip("存在相同链接地址");
				// }
				menu.setId(UUIDTool.getIntId());
				// 单独处理索引
				Object obj = superService.getRecordIndex(Menu.class);
				if (obj != null) {
					Menu m = (Menu) obj;
					menu.setRecordIndex((Integer.valueOf(m.getRecordIndex()) + 1)
							+ "");
				} else {
					menu.setRecordIndex("1");
				}
				menu.setCommitTime(new Date(System.currentTimeMillis()));
				menuService.save(menu);
			}
			return TipEnum.SAVE.indexTip();
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.SAVE_FAILE.indexTip();
	}

	// 删除数据
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public @ResponseBody
	Object delete(@RequestParam(value = "id", required = false) String id) {
		try {
			if (superService.delEntity(new Menu(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}

	
	// 加载栏目菜单
			@RequestMapping(params = "loadingMenu", method = RequestMethod.POST)
			public @ResponseBody
			Object loadingMenu() {
				try {
					return TreeUtil.comBoxMenu(menuService);
				} catch (Exception e) {
					new CustomException(e);
				}
				return null;
			}
	// 系统初始化左侧菜单栏
	@RequestMapping(params = "startMenu", method = RequestMethod.GET)
	public @ResponseBody
	Object startMenu(HttpServletRequest request) {
		try {
			//return TreeUtil.startMenu(menuService);
			return Session.getValue(request, Session.getValue(request, "userIndex")+"_Menu");
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	
}
