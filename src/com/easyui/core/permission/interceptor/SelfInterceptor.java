package com.easyui.core.permission.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.easyui.core.exception.CustomException;
import com.easyui.core.util.PowerPropertie;
import com.easyui.core.util.Session;

public class SelfInterceptor extends HandlerInterceptorAdapter {

	// 记录日志
	final Logger logger = LoggerFactory.getLogger(getClass());

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView view)
			throws Exception {
		String contextPath = request.getContextPath();
		if (view != null) {
			request.setAttribute("base", contextPath);
		}
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean flag = false;
		logger.debug(">>>启动用户身份验证");
		String[] powers = PowerPropertie.getVal("YES_POWER").split(",");
		String url = request.getRequestURI();
		for (String path : powers) {
			if (path.equalsIgnoreCase(url)) {
				// 免验证请求
				flag = true;
				break;
			}
		}
		if (!flag) {
			try {
				// 处理Permission Annotation，实现方法级权限控制
				String userIndex = Session.getValue(request, "userIndex");
				String fire = Session.getValue(request, userIndex + "_User") == null ? ""
						: Session.getValue(request, userIndex + "_User")
								.replace("[", "").replace("]", "");
				if (userIndex != null && fire != null) {
					if (fire.contains(userIndex)) {
						logger.debug(">>>用户身份验证通过");
						return true;
					}
				}
			} catch (Exception e) {
				new CustomException(e);
			}
		} else {
			logger.debug(">>>通过验证");
			return true;
		}
		// 注意此处必须返回true，否则请求将停止
		// return true;
		logger.debug(">>>身份认证失败");

		// request.getRequestDispatcher(PowerPropertie.getVal("page")).forward(request,
		// response);
		// response.flushBuffer();
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script type=\"text/javascript\">");
		out.println("window.open ('" + PowerPropertie.getVal("page")
				+ "','_top')");
		out.println("</script>");
		out.println("</html>");
		out.flush();

		return false;
	}
}
