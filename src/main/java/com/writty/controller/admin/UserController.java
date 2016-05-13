package com.writty.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.model.User;
import com.writty.service.UserService;

import blade.kit.StringKit;

/**
 * 用户管理
 */
@Path("/admin/")
public class UserController extends BaseController {
	
	@Inject
	private UserService userService;
	
	/**
	 * 用户管理
	 */
	@Route(value = "/users")
	public ModelAndView show_posts(Request request, Response response){
		Integer page = request.queryAsInt("p");
		String login_name = request.query("login_name");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam up = QueryParam.me();
		if(StringKit.isNotBlank(login_name)){
			up.eq("login_name", login_name);
			request.attribute("login_name", login_name);
		}
		up.orderby("update_time desc").page(page, 15);
		Page<User> userPage = userService.getPageList(up);
		request.attribute("userPage", userPage);
		return this.getAdminView("users");
	}
	
	/**
	 * 设置用户状态
	 */
	@Route(value = "/users/status")
	public void updateStatus(Request request, Response response){
		String type = request.query("type");
		Long uid = request.queryAsLong("uid");
		
		if(StringKit.isBlank(type) || null == uid){
			this.error(response, "缺少参数");
			return;
		}
		
		Integer role_id = null;
		if(type.equals("editor")){
			role_id = 3;
		}
		
		if(type.equals("admin")){
			role_id = 2;
		}
		
		if(null != role_id){
			userService.updateRole(uid, role_id);
		}
		this.success(response, "");
	}
	
}
