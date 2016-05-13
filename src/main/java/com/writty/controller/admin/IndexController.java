package com.writty.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.service.UserService;

@Path("/admin/")
public class IndexController extends BaseController {
	
	@Inject
	private UserService userService;
	
	/**
	 * 首页
	 */
	@Route(value = "/", method = HttpMethod.GET)
	public ModelAndView show_home(Request request, Response response){
		return this.getAdminView("home");
	}
	
	/**
	 * 文章管理
	 */
	@Route(value = "/posts", method = HttpMethod.GET)
	public ModelAndView show_posts(Request request, Response response){
		return this.getAdminView("posts");
	}
	
	/**
	 * 附件管理
	 */
	@Route(value = "/attachs", method = HttpMethod.GET)
	public ModelAndView show_attachs(Request request, Response response){
		return this.getAdminView("attachs");
	}
	
	/**
	 * 友链管理
	 */
	@Route(value = "/links", method = HttpMethod.GET)
	public ModelAndView show_links(Request request, Response response){
		return this.getAdminView("links");
	}
	
}
