package com.writty.controller.admin;

import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.service.PostService;
import com.writty.service.UserService;

import blade.kit.StringKit;

@Path("/admin/")
public class IndexController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private PostService postService;
	
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
	@Route(value = "/posts")
	public ModelAndView show_posts(Request request, Response response){
		String title = request.query("title");
		String is_pub_str = request.query("is_pub");
		Integer is_pub = StringKit.equals(is_pub_str, "on") ? 0 : null;
		Integer page = request.queryAsInt("p");
		
		Page<Map<String, Object>> postPage = postService.getPageListMap(null, null, is_pub, title, page, 15, "is_pub asc, created desc");
		request.attribute("postPage", postPage);
		request.attribute("title", title);
		request.attribute("is_pub", is_pub);
		
		return this.getAdminView("posts");
	}
	
	/**
	 * 删除文章
	 */
	@Route(value = "/posts/delete")
	public void delete_post(Request request, Response response){
		String pid = request.query("pid");
		boolean flag = postService.delete(pid);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "删除失败");
		}
	}
	
	/**
	 * 审核文章
	 */
	@Route(value = "/posts/audit")
	public void audit_post(Request request, Response response){
		String pid = request.query("pid");
		boolean flag = postService.audit(pid);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "删除失败");
		}
	}
	
	/**
	 * 友链管理
	 */
	@Route(value = "/links", method = HttpMethod.GET)
	public ModelAndView show_links(Request request, Response response){
		return this.getAdminView("links");
	}
	
}
