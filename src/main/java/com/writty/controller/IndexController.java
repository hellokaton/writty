package com.writty.controller;

import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.Constant;
import com.writty.kit.SessionKit;
import com.writty.model.User;
import com.writty.service.CommentService;
import com.writty.service.PostService;
import com.writty.service.SpecialService;
import com.writty.service.UserService;

import blade.kit.StringKit;

@Path("/")
public class IndexController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private SpecialService specialService;
	
	@Inject
	private PostService postService;
	
	@Inject
	private CommentService commentService;
	
	/**
	 * 首页
	 */
	@Route(value = "/", method = HttpMethod.GET)
	public ModelAndView show_home(Request request, Response response){
		
		List<Map<String, Object>> specials = specialService.getRandomList();
		request.attribute("specials", specials);
		
		return this.getView("home");
	}
	
	/**
	 * 登录页面
	 */
	@Route(value = "/signin", method = HttpMethod.GET)
	public ModelAndView show_signin(Request request, Response response){
		return this.getView("signin");
	}

	/**
	 * 关于页面
	 */
	@Route(value = "/about", method = HttpMethod.GET)
	public ModelAndView show_about(Request request, Response response){
		return this.getView("about");
	}
	
	/**
	 * 发现页面
	 */
	@Route(value = "/explore", method = HttpMethod.GET)
	public ModelAndView show_explore(Request request, Response response){
		Integer page = request.queryAsInt("p");
		Page<Map<String, Object>> postPage = postService.getPageListMap(null, null, null, null, page, 10);
		request.attribute("postPage", postPage);
		return this.getView("explore");
	}
	
	/**
	 * 专栏页面
	 */
	@Route(value = "/specials", method = HttpMethod.GET)
	public ModelAndView show_specials(Request request, Response response){
		Integer page = request.queryAsInt("p");
		Page<Map<String, Object>> specialPage = specialService.getPageListMap(null, 1, page, 6, null);
		request.attribute("specialPage", specialPage);
		return this.getView("specials");
	}
	
	/**
	 * 查看专栏详情
	 */
	@Route(value = "/s/:id", method = HttpMethod.GET)
	public ModelAndView showSpecialDetail(@PathVariable("id") Long id, Request request, Response response){
		Map<String, Object> specialMap = specialService.getSpecialMap(null, id);
		request.attribute("specialMap", specialMap);
		
		Integer page = request.queryAsInt("p");
		
		Page<Map<String, Object>> postPage = postService.getPageListMap(null, id, null, null, page, 10);
		
		request.attribute("postPage", postPage);
		
		return this.getView("special_detail");
	}
	
	/**
	 * 查看文章详情
	 */
	@Route(value = "/p/:pid", method = HttpMethod.GET)
	public ModelAndView showArticle(@PathVariable("pid") String pid, Request request, Response response){
		
		Map<String, Object> postMap = postService.getPostDetail(null, pid);
		request.attribute("postMap", postMap);
		
		List<Map<String, Object>> comments = commentService.getCommentListMap(pid);
		request.attribute("comments", comments);
		
		return this.getView("post_detail");
	}
	
	/**
	 * 登录
	 */
	@Route(value = "/signin", method = HttpMethod.POST)
	public String signin(Request request, Response response){
		String userName = request.query("user_name");
		String passWord = request.query("pass_word");
		if(StringKit.isBlank(userName) || StringKit.isBlank(passWord)){
			request.attribute(this.ERROR, "用户名和密码不能为空");
			return "signin.html";
		}
		
		User user = userService.signin(userName, passWord);
		if(null == user){
			request.attribute("user_name", userName);
			request.attribute(this.ERROR, "用户名或密码错误");
			return "signin.html";
		}
		
		String rememberMe = request.query("remember_me");
		if(StringKit.isNotBlank(rememberMe) && rememberMe.equals("on")){
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, user.getUid());
		}
		SessionKit.setLoginUser(request.session(), user);
		response.go("/");
		return null;
	}
	
	/**
	 * 注销登录
	 */
	@Route(value = "/logout", method = HttpMethod.GET)
	public ModelAndView logout(Request request, Response response){
		SessionKit.removeCookie(response);
		SessionKit.removeUser(request.session());
		response.go("/");
		return null;
	}
	
}
