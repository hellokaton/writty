package com.writty.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.AR;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.service.UserService;

import blade.kit.StringKit;

@Path("/admin/")
public class IndexController extends BaseController {
	
	@Inject
	private UserService userService;
	
	/**
	 * 首页
	 */
	@Route(value = "/")
	public ModelAndView show_home(Request request, Response response){
		return this.getAdminView("home");
	}
	
	/**
	 * 系统工具
	 */
	@Route(value = "tools")
	public ModelAndView show_tools(Request request, Response response){
		return this.getAdminView("tools");
	}
	
	/**
	 * 执行系统工具
	 */
	@Route(value = "tools", method = HttpMethod.POST)
	public ModelAndView save_tools(Request request, Response response){
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			request.attribute(this.ERROR, "请选择执行的操作");
			return this.getAdminView("tools");
		}
		
		if(type.equals("clean_cache")){
			AR.cleanCache();
			request.attribute(this.INFO, "执行成功");
			return this.getAdminView("tools");
		}
		
		return this.getAdminView("tools");
	}
	
}
