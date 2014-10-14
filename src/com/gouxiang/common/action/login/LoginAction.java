package com.gouxiang.common.action.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gouxiang.common.entity.Permission;
import com.gouxiang.common.entity.User;
import com.gouxiang.core.exception.CustomException;
import com.gouxiang.core.service.SuperService;
import com.gouxiang.core.util.Session;
import com.gouxiang.core.util.TipEnum;
import com.gouxiang.core.util.TreeUtil;
import com.gouxiang.web.service.MenuService;
import com.gouxiang.web.service.UserService;
import com.gouxiang.web.sql.SQL;

/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2014
 * Class:			GetCaptcha
 * Date:			2014-6-22
 * Author:			<a href="mailto:mrchenyazhou@163.com">mrchenyazhou</a>
 * Version          1.1.0
 * Description:		用户登录
 * </pre>
 **/
@Controller
@RequestMapping("/login")
public class LoginAction {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private SuperService superService;

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "check", method = RequestMethod.POST)
	public @ResponseBody
	Object login(
			HttpServletRequest request,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "passWord", required = false) String passWord) {
		User user = userService.queryByNP(userName, passWord, User.class);
		if (user != null) {
			//将用户信息放到session中
			Session.setSession(request,user);
			//将用户拥有的权限放到session中
			List<Permission> pers=  (List<Permission>) superService.getEntitysSQL(Permission.class, SQL.LOGINACTION_PERMISSION_P.value(user.getRecordIndex()));
			if(pers!=null&&pers.size()>0){
				Session.setSelfParms(request, user.getRecordIndex()+"_Menu", TreeUtil.dMenu(pers));
				Session.setSelfParms(request,user.getRecordIndex()+"_User", TreeUtil.dUser(pers));
				//记录当前用户的痕迹
				userService.recordLog(request,TipEnum.LOGIN.indexTip());
				return TipEnum.LOGIN.indexTip();
			}else{
				//记录当前用户的痕迹
				userService.recordLog(request,TipEnum.NO_POWER.indexTip());
				return TipEnum.NO_POWER.indexTip();
			}
		} else {
			//记录当前用户的痕迹
			userService.recordLog(request,TipEnum.LOGIN_FAILE.indexTip());
			return TipEnum.LOGIN_FAILE.indexTip();
		}

	}


	// 退出系统清理session
	@RequestMapping(params = "exit")
	public void exit(HttpServletRequest request,HttpServletResponse response) {
		try {
			System.out
					.println(">>>Start Clear User Session");
			userService.recordLog(request,TipEnum.EXIT.indexTip());
			System.out.println(">>>"+Session.clear(request));
			System.out
					.println(">>>Clear User Session Seccuss!");
			response.sendRedirect("./login.htm");
		} catch (IOException e) {
			new CustomException(e);
		}
	}

}
