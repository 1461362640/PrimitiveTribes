package com.easyui.web.action.permission;

import httl.Engine;
import httl.Template;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.easyui.common.entity.OS;
import com.easyui.common.entity.Permission;
import com.easyui.common.entity.User;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.ID;
import com.easyui.core.util.OSUtil;
import com.easyui.core.util.TipEnum;
import com.easyui.core.util.ToJSON;
import com.easyui.core.util.TreeUtil;
import com.easyui.core.util.UUIDTool;
import com.easyui.web.service.MenuService;
import com.easyui.web.service.UserService;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			BannerAction.java
 * Date:			2014-6-29
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		横幅列表
 * </pre>
 **/
@Controller
@RequestMapping("/permission.do")
public class PermissionAction {
	@Autowired
	private SuperService superService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public PermissionAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void list(HttpServletResponse response, ModelMap modelMap) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/permission/permission.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "permissionList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort=request.getParameter("sort");
		String order=request.getParameter("order");
		try {
			BigInteger total=superService.getCount(Permission.class);
			List<?> permission = superService.findAll(Permission.class, page, rows,sort,order,total);
			return ToJSON.toJSON(JSON.toJSONString(permission),total);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "savePermission")
	public @ResponseBody
	Object saveVideo(@RequestParam(value = "id", required = false) String id,
			Permission permission) {
		try {
			if (id != null && !"".equalsIgnoreCase(id)) {
				permission.setId(id.split(",")[0]);
				superService.saveOrUpdate(permission);
			} else {
				permission.setId(UUIDTool.getIntId());
				superService.save(permission);
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
			if (superService.delEntity(new Permission(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}

	// 加载栏目数据转换成json数据
	@RequestMapping(params = "loadMenu", method = RequestMethod.POST)
	public @ResponseBody
	Object loadMenu(HttpServletRequest request) {
		try {
			//根据当前用户id加载用户选中效果
			//String id=Session.getValue(request, "userIndex");
			
			return TreeUtil.toTreeJson(menuService);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 系统权限分配用户列表
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "userDatas", method = RequestMethod.POST)
	public @ResponseBody
	Object userDatas(HttpServletRequest request) {
		try {
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			List<User> users = (List<User>) superService.findAll(User.class,
					page, rows);
			return ToJSON.toJSON(JSON.toJSONString(users),
					superService.getCount(User.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存权限规则
	@RequestMapping(params = "saveRule", method = RequestMethod.POST)
	public @ResponseBody
	Object saveRule(@RequestParam("id") String id, HttpServletRequest request) {
		try {
			String menuIds = request.getParameter("menuIds");
			String userIds = request.getParameter("userIds");
			Permission pers = new Permission();
			pers.setUserIds(userIds);
			pers.setMenuIds(menuIds);
			if (!ID.isNull(id)) {
				Permission per = (Permission) superService.getEnity(
						Permission.class, ID.id_Split(id));
				per.setUserIds(userIds);
				per.setMenuIds(menuIds);
				superService.saveOrUpdate(per);
			} else {
				superService.save(pers);
			}

			return TipEnum.SAVE.indexTip();
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.SAVE_FAILE.indexTip();
	}

	
	// 读取系统相关信息
		@RequestMapping(params = "osInfo", method = RequestMethod.GET)
		public @ResponseBody
		Object osInfo() {
			 OS os=new OS();
			 os.setJavaHome(OSUtil.getProperty("java.home"));
			 os.setJavaVendor(OSUtil.getProperty("java.vendor"));
			 os.setJavaVendorUrl("java.vendor.url");
			 os.setJdkVersion(OSUtil.getProperty("java.version"));
			 os.setLoadLibPath(OSUtil.getProperty("java.library.path"));
			 os.setOsArch(OSUtil.getProperty("os.arch"));
			 os.setOsName(OSUtil.getProperty("os.name"));
			 os.setOsVersion(OSUtil.getProperty("os.version"));
			 os.setProjectPath(OSUtil.getProperty("user.dir"));
			 os.setTmpFile(OSUtil.getProperty("java.io.tmpdir"));
			 os.setUserHome(OSUtil.getProperty("user.home"));
			 return JSON.toJSONString(os);
		}
		// 读取系统相关信息
			@RequestMapping(params = "cpuComb", method = RequestMethod.GET)
			public @ResponseBody Object cpuComb(){
				return JSON.toJSONString(OSUtil.cpuComb());
			}
}
