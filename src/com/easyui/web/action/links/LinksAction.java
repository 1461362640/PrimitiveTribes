package com.easyui.web.action.links;

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
import com.easyui.common.entity.Links;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.TipEnum;
import com.easyui.core.util.ToJSON;
import com.easyui.core.util.UUIDTool;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			GetCaptcha
 * Date:			2014-8-31
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		友情链接
 * </pre>
 **/
@Controller
@RequestMapping("/links.do")
public class LinksAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public LinksAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void linksList(HttpServletResponse response, ModelMap modelMap) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/links/links.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
		// return null;
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "linksList", method = RequestMethod.GET)
	public @ResponseBody Object returnJSON(HttpServletRequest request,
			@RequestParam(value = "page") String page,
			@RequestParam(value = "rows") String rows,
			@RequestParam(value = "sort",required=false) String sort,
			@RequestParam(value = "order",required=false) String order) {
		try {
			BigInteger total = superService.getCount(Links.class);
			List<?> links = superService.findAll(Links.class, page, rows, sort,
					order, total);
			return ToJSON.toJSON(JSON.toJSONString(links), total);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveLinks", method = RequestMethod.POST)
	public @ResponseBody Object saveVideo(
			@RequestParam(value = "id", required = false) String id, Links links) {
		try {
			if (id != null && !"".equalsIgnoreCase(id)) {
				superService.saveOrUpdate(links);
				return TipEnum.UPDATE.indexTip();
			} else {
				links.setId(UUIDTool.getIntId());
				superService.save(links);
				return TipEnum.SAVE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.SAVE_FAILE.indexTip();
	}

	// 删除数据
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public @ResponseBody Object delete(
			@RequestParam(value = "ids", required = false) String ids) {
		try {
			if (superService.delete(Links.class, UUIDTool.convIdToStr(ids)))
				// if (superService.delEntity(new Links(id))) {
				return TipEnum.DELETE.indexTip();
			// }
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "linkJSON", method = RequestMethod.GET)
	public @ResponseBody Object linkJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		try {
			List<?> links = superService.findAll(Links.class, page, rows);
			return JSON.toJSONString(links);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}
}
