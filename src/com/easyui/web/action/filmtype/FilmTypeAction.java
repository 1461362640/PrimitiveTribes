package com.easyui.web.action.filmtype;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.easyui.common.entity.FilmType;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.TipEnum;
import com.easyui.core.util.ToJSON;
import com.easyui.core.util.UUIDTool;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			GetCaptcha
 * Date:			2014-6-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		视频类型列表
 * </pre>
 **/
@Controller
@RequestMapping("/filmtype.do")
public class FilmTypeAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public FilmTypeAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void filmTypeList(HttpServletResponse response, ModelMap modelMap) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/filmtype/filmtype.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
		// return null;
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "filmTypeList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows =request.getParameter("rows");
		try {
			List<?> filmTypes = superService
					.findAll(FilmType.class, page, rows);
			return ToJSON.toJSON(JSON.toJSONString(filmTypes),
					superService.getCount(FilmType.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveFilmType", method = RequestMethod.POST)
	public @ResponseBody
	Object saveVideo(@RequestParam(value="id",required=false)String id,FilmType filmType) {
		try {
			if(id!=null&&!"".equalsIgnoreCase(id)){
				superService.saveOrUpdate(filmType);
				return TipEnum.UPDATE.indexTip();
			}else{
				filmType.setId(UUIDTool.getIntId());
				superService.save(filmType);
				return TipEnum.SAVE.indexTip();
			}
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
			if (superService.delEntity(new FilmType(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}

}
