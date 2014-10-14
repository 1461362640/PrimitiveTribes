package com.gouxiang.common.pc.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gouxiang.common.entity.Video;
import com.gouxiang.common.pc.server.json.JSONUtil;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Filename:		com.easyui.common.pc.server.HTTP.java
 * Class:			HTTP
 * Date:			2014-5-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		客户端
 * </pre>
 **/
@Controller
@RequestMapping("/docking.do")
public class HTTP {

	@Autowired
	private SuperService superService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "dock", method = RequestMethod.GET)
	public @ResponseBody
	Object docking() {
		try {
			List<Video> videos=(List<Video>) superService.findAll(Video.class);
		return JSONUtil.objectToStr(videos,superService.getCount(Video.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

}
