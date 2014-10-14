package com.gouxiang.common.action.forward;

import httl.Engine;
import httl.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gouxiang.common.entity.Permission;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.MenuUtil;
import com.gouxiang.core.util.Session;
import com.gouxiang.core.util.TreeUtil;
import com.gouxiang.web.service.MenuService;
import com.gouxiang.web.sql.SQL;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Filename:		com.xilei.edu.common.controller.login.Init.java
 * Class:			Init
 * Date:			2014-5-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		对于框架资源进行初始包含栏目的初始化
 * </pre>
 **/
@Controller
@RequestMapping("/forward.do")
public class ForwardAction {
	// 记录日志
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SuperService superService;
	@Autowired
	private MenuService menuService;

	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "forward", method = RequestMethod.GET)
	public void forWard(HttpServletRequest request, HttpServletResponse response) {
		try {
			engine = Engine.getEngine("httl.properties");
			// 得到该用户的权限组信息
			// String menuIds=Session.getValue(request,
			// Session.getValue(request, "userIndex")+"_Menu");
			String userIndex = "0";
			Object userObj = request.getSession().getAttribute("userIndex");
			if (userObj != null) {
				userIndex = (String) userObj;
			}
			//读取当前用户栏目权限
			List<Permission> permissions=(List<Permission>) superService.getEntitysSQL(Permission.class, SQL.LOGINACTION_PERMISSION_P.value(userIndex));
			String menuIds=TreeUtil.dMenu(permissions);
			if(menuIds!=null&&!"".equalsIgnoreCase(menuIds)){
				// 左侧栏目菜单
			new	MenuUtil().putMenu(request,menuService,userIndex,menuIds.replace("[", "").replace("]",""));
			}
			// 左侧栏目菜单
//			Object obj = MenuUtil.startMenu(menuService);
//			if (obj != null && userIndex != null) {
//				Session.setSelfParms(request, userIndex + "_Menu", obj);
//			}
			parameters.put("user", Session.getValue(request, "user"));
			template = engine.getTemplate("/root.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}
}
