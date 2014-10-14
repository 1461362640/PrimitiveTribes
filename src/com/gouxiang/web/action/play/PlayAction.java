package com.gouxiang.web.action.play;

import httl.Engine;
import httl.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gouxiang.common.entity.Video;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			PlayAction.java
 * Date:			2014-6-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		视频播放
 * </pre>
 **/
@Controller
@RequestMapping("/playvideo.do")
public class PlayAction {
	@Autowired
	private SuperService superService;
	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public PlayAction() {
		super();
	}

	@RequestMapping(params = "play", method = RequestMethod.GET)
	public void userList(HttpServletResponse response,
			@RequestParam("id") String id, @RequestParam("type") String type) {
		try {
			engine = Engine.getEngine("httl.properties");
			Object video = superService.getEnity(Video.class, id);
			List<?> videos = superService.getEntitysHQL(Video.class,
					"where id<>'" + id + "' and type='" + type
							+ "' order by uploadDate desc");
			parameters.put("video", video);
			parameters.put("videos", videos);
			template = engine.getTemplate("/play/play.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

}
