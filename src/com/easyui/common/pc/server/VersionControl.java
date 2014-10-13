package com.easyui.common.pc.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easyui.common.entity.Version;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Filename:		com.easyui.common.pc.server.HTTP.java
 * Class:			HTTP
 * Date:			2014-5-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		软件版本控制
 * </pre>
 **/

@Controller
@RequestMapping("/version.do")
public class VersionControl {

	@Autowired
	private SuperService superService;

	@RequestMapping(params = "version", method = RequestMethod.GET)
	public @ResponseBody
	Object version() {
		try {
			String versionSql = " order by uploadDate desc";
			List<?> list = superService
					.getEntitysHQL(Version.class, versionSql);
			Version version = (Version) list.get(0);
			return version.getVersion() + "#" + version.getUrl()+"#"+version.getName();
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

}
