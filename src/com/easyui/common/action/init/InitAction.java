package com.easyui.common.action.init;

import httl.Engine;
import httl.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.easyui.common.entity.Banner;
import com.easyui.common.entity.Video;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;

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
@RequestMapping("/init.do")
public class InitAction {
	// 记录日志
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SuperService superService;
	//模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	@RequestMapping(value = "init",method = RequestMethod.GET)
	public void userList(ModelMap modelMap,HttpServletResponse response) {
		try {
			engine = Engine.getEngine("httl.properties");
			//List<?> videos = superService.findAll(Video.class,0,5);
			List<?> mvs=superService.getEntitysHQL(Video.class,"where type='f90c067a02cb4301abb7bd233522eca1' order by uploadDate desc  limit 0,7");//mv
			List<?> erzs=superService.getEntitysHQL(Video.class,"where type='a8906494a66d4c91b70a98831bf6ed9f' order by uploadDate desc  limit 0,7");//二人转
			List<?> dys=superService.getEntitysHQL(Video.class,"where type='188ae7bc320b4c6b821d5da5f24bb9cb' order by uploadDate desc  limit 0,7");//电影
			//首页轮播
			List<?> banners=superService.findAll(Banner.class);
			parameters.put("banners",banners);
			//视频列表
			parameters.put("mvs", mvs);
			parameters.put("erzs", erzs);
			parameters.put("dys", dys);
			template = engine.getTemplate("/index.jsp");
			template.render(parameters, response.getOutputStream());
			//modelMap.put("videos",videos );
			//return "index";
		} catch (Exception e) {
			new CustomException(e);
		}
	}
}
