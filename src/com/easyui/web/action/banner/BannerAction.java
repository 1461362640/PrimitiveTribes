package com.easyui.web.action.banner;

import httl.Engine;
import httl.Template;

import java.sql.Date;
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
import com.easyui.common.entity.Banner;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.TipEnum;
import com.easyui.core.util.ToJSON;
import com.easyui.core.util.UUIDTool;

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
@RequestMapping("/banner.do")
public class BannerAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public BannerAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void bannerList(HttpServletResponse response, ModelMap modelMap) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/banner/banner.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "bannerList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		try {
			List<?> videos = superService.findAll(Banner.class, page, rows);
			return ToJSON.toJSON(JSON.toJSONString(videos),
					superService.getCount(Banner.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveBanner")
	public @ResponseBody
	Object saveVideo(@RequestParam(value = "id", required = false) String id,
			Banner banner) {
		try {
			if (id != null && !"".equalsIgnoreCase(id)) {
				banner.setEditDate(new Date(System.currentTimeMillis()));
				superService.saveOrUpdate(banner);
			} else {
				banner.setId(UUIDTool.getIntId());
				banner.setUploadDate(new Date(System.currentTimeMillis()));
				superService.save(banner);
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
			if (superService.delEntity(new Banner(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}
}
