package com.easyui.web.action.user;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.easyui.common.entity.User;
import com.easyui.core.exception.CustomException;
import com.easyui.core.service.SuperService;
import com.easyui.core.util.TipEnum;
import com.easyui.core.util.ToJSON;
import com.easyui.core.util.UUIDTool;
import com.easyui.web.service.UserService;

@Controller
@RequestMapping("/user.do")
public class UserAction {
	@Autowired
	private SuperService superService;

	@Autowired
	private UserService userService;

	// 模板引擎初始
	private Map<String, Object> parameters = new HashMap<String, Object>();// 参数集合

	private Template template;// httl模板加载

	private Engine engine;// httl模板引擎

	public UserAction() {
		super();
	}

	@RequestMapping(params = "list", method = RequestMethod.GET)
	public void userList(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			engine = Engine.getEngine("httl.properties");
			template = engine.getTemplate("/user/user.jsp");
			template.render(parameters, response.getOutputStream());
		} catch (Exception e) {
			new CustomException(e);
		}
	}

	/** json方式获取数据列表 **/
	@RequestMapping(params = "userList", method = RequestMethod.GET)
	public @ResponseBody
	Object returnJSON(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		try {
			List<?> UserList = superService.findAll(User.class,page, rows);
			return ToJSON.toJSON(JSON.toJSONString(UserList),
					superService.getCount(User.class));
		} catch (Exception e) {
			new CustomException(e);
		}
		return null;
	}

	// 保存数据
	@RequestMapping(params = "saveUser")
	public @ResponseBody
	Object saveUser(@RequestParam("id") String id,User user) {
		try {
			if (id != null && !"".equalsIgnoreCase(id)) {
				user.setId(id.split(",")[0]);
				user.setModifyDate(new Date(System.currentTimeMillis()));
				superService.saveOrUpdate(user);
			} else {
				user.setId(UUIDTool.getIntId());
				// 单独处理索引
				Object obj = superService.getRecordIndex(User.class);
				if (obj != null) {
					User u = (User) obj;
					user.setRecordIndex((Integer.valueOf(u.getRecordIndex()) + 1)+"");
				} else {
					user.setRecordIndex("1");
				}
				user.setPassword("000000");
				user.setRegisterDate(new Date(System.currentTimeMillis()));
				superService.save(user);
			}
			return TipEnum.SAVE.indexTip();
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
			if (superService.delEntity(new User(id))) {
				return TipEnum.DELETE.indexTip();
			}
		} catch (Exception e) {
			new CustomException(e);
		}
		return TipEnum.DELETE_FAILE.indexTip();
	}
}
