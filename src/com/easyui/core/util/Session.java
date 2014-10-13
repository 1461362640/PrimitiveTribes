package com.easyui.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.easyui.common.entity.User;
import com.easyui.core.exception.CustomException;

/**
 * 设置用户登录后的session信息
 * 
 * @author Chenyz
 * 
 */
public class Session {

	public static void setSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user.getName());
		session.setAttribute("userId", user.getId());
		session.setAttribute("userIndex", user.getRecordIndex());
	}

	public static String getValue(HttpServletRequest request, String key) {
		Object obj = request.getSession().getAttribute(key);
		if (obj != null) {
			return obj.toString();
		}

		return null;
	}

	// 放入自定义参数
	public static void setSelfParms(HttpServletRequest request, String key,
			Object value) {
		request.getSession().setAttribute(key, value);
	}

	// 清理所有session
	public static boolean clear(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("userId");
			session.removeAttribute("userIndex");
			return true;
		} catch (Exception e) {
			new CustomException(e);
		}
		return false;

	}
}
