package com.gouxiang.common.action.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			GetCaptcha
 * Date:			2014-6-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		获取验证 
 * </pre>
 **/
@Controller
@RequestMapping("/captcha.do")
public class GetCaptchaAction {
	@Autowired
	private Producer captchaProducer = null;
	
	protected final transient Log log=LogFactory.getLog(GetCaptchaAction.class);

	@RequestMapping(params = "getCode")
	public void getCaptcha(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 获得session
		HttpSession session = request.getSession();

		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = captchaProducer.createText();

		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		ServletOutputStream out = null;
		try {
			// create the image with the text
			BufferedImage bi = captchaProducer.createImage(capText);
			out = response.getOutputStream();

			// write the data out
			ImageIO.write(bi, "jpg", out);
			out.flush();

			// 获得验证 
			String code = (String) session
					.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			log.debug("----------------------Captcha is :" + code
					+ "----------------------");
		} finally {
			out.close();
		}
	}
}
