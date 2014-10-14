package com.gouxiang.web.action.video;

import httl.Engine;
import httl.Template;

import java.util.Date;
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
import com.gouxiang.common.entity.Banner;
import com.gouxiang.common.entity.FilmType;
import com.gouxiang.common.entity.Video;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.TipEnum;
import com.gouxiang.core.util.ToJSON;
import com.gouxiang.core.util.UUIDTool;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			GetCaptcha
 * Date:			2014-6-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		视频列表
 * </pre>
 **/
@Controller
@RequestMapping("/video.do")
public class VideoAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public VideoAction() {
		super();
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void videoList(HttpServletResponse response) {
		try {
			engine = Engine.getEngine("httl.properties");
			// List<?> videos = superService.findAll(Video.class);
			// parameters.put("video",
			// ToJSON.toJSON(JSON.toJSONString(videos)));
			template = engine.getTemplate("/video/video.jsp");
			template.render(parameters, response.getOutputStream());
			// modelMap.put("video", ToJSON.toJSON(JSON.toJSONString(videos)));
			// return "/video/video";
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "videoList")
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		try {
			List<?> videos = superService.findAll(Video.class, page, rows);
			return ToJSON.toJSON(JSON.toJSONString(videos),
					superService.getCount(Video.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	@RequestMapping(params = "filmTypeList")
	public @ResponseBody
	Object filmType(HttpServletRequest request) {
		try {
			List<?> filmTypes = superService.findAll(FilmType.class);
			return JSON.toJSONString(filmTypes);
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveVideo")
	public @ResponseBody
	Object saveVideo(@RequestParam(value = "id", required = false) String id,
			Video video) {
		try {
			if (id != null && !"".equalsIgnoreCase(id)) {
				video.setEditDate(new Date(System.currentTimeMillis()));
				superService.saveOrUpdate(video);
				return TipEnum.UPDATE.indexTip();
			} else {
				video.setId(UUIDTool.getIntId());
				video.setUploadDate(new Date(System.currentTimeMillis()));
				superService.save(video);
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
			if (superService.delEntity(new Video(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}

	@RequestMapping(value = "mv", method = RequestMethod.GET)
	public void mvList(ModelMap modelMap, HttpServletResponse response,
			@RequestParam(value = "id") String id) {
		try {
			engine = Engine.getEngine("/WEB-INF/httl.properties");
			List<?> mvs = superService.getEntitysHQL(Video.class,
					"where type='" + id + "' order by uploadDate desc");// mv
			// 首页轮播
			List<?> banners = superService.findAll(Banner.class);
			parameters.put("banners", banners);
			// 视频列表
			parameters.put("mvs", mvs);
			template = engine.getTemplate("/mv_list.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	@RequestMapping(value = "erz", method = RequestMethod.GET)
	public void erzList(ModelMap modelMap, HttpServletResponse response,
			@RequestParam(value = "id") String id) {
		try {
			engine = Engine.getEngine("/WEB-INF/httl.properties");
			List<?> mvs = superService.getEntitysHQL(Video.class,
					"where type='" + id + "' order by uploadDate desc");// mv
			// 首页轮播
			List<?> banners = superService.findAll(Banner.class);
			parameters.put("banners", banners);
			// 视频列表
			parameters.put("erzs", mvs);
			template = engine.getTemplate("/erz_list.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	@RequestMapping(value = "dy", method = RequestMethod.GET)
	public void dyList(ModelMap modelMap, HttpServletResponse response,
			@RequestParam(value = "id") String id) {
		try {
			engine = Engine.getEngine("/WEB-INF/httl.properties");
			List<?> mvs = superService.getEntitysHQL(Video.class,
					"where type='" + id + "' order by uploadDate desc");// mv
			// 首页轮播
			List<?> banners = superService.findAll(Banner.class);
			parameters.put("banners", banners);
			// 视频列表
			parameters.put("dys", mvs);
			template = engine.getTemplate("/dy_list.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}
}
