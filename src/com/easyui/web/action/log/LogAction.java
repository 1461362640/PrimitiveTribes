package com.easyui.web.action.log;

import httl.Engine;
import httl.Template;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.easyui.common.entity.Log;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.ToJSON;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			LinksAction.java
 * Date:			2014-8-31
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		系统日志
 * </pre>
 **/
@Controller
@RequestMapping("/log.do")
public class LogAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public LogAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void logList(HttpServletResponse response, ModelMap modelMap) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/log/log.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
		// return null;
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "logList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows =request.getParameter("rows");
		try {
			List<?> logs = superService
					.findAll(Log.class, page, rows);
			return ToJSON.toJSON(JSON.toJSONString(logs),
					superService.getCount(Log.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}
	
}
