package com.writty.controller;

import java.io.File;
import java.util.List;

import com.blade.Blade;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.kit.SessionKit;
import com.writty.kit.Utils;
import com.writty.model.Special;
import com.writty.model.User;
import com.writty.service.CommentService;
import com.writty.service.FavoriteService;
import com.writty.service.PostService;
import com.writty.service.SpecialService;
import com.writty.service.UserService;

import blade.kit.StringKit;

@Path("/")
public class ArticleController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private PostService postService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private SpecialService specialService;
	
	/**
	 * 写文章页面
	 */
	@Route(value = "/write", method = HttpMethod.GET)
	public ModelAndView show_write(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		
		QueryParam where = QueryParam.me();
		where.gt("id", 0);
		where.orderby("post_count desc");
		List<Special> specials = specialService.getSpecialList(where);
		request.attribute("specials", specials);
		
		return this.getView("write");
	}
	
	/**
	 * 保存文章
	 */
	@Route(value = "/write", method = HttpMethod.POST)
	public void save_write(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		String title = request.query("title");
		String cover = request.query("post_cover");
		String content = request.query("content");
		Long sid = request.queryAsLong("sid");
		Integer is_pub = request.queryAsInt("is_pub");
		Integer type = request.queryAsInt("type");
		
		if(StringKit.isBlank(title) || 
				StringKit.isBlank(content) ||
				null == sid || null == is_pub || null == type){
			this.error(response, "参数不能为空");
			return;
		}
		
		if(title.length() < 5 || title.length() > 50){
			this.error(response, "请输入5-20个字符长度的标题");
			return;
		}
		
		if(content.length() < 50){
			this.error(response, "请输入50字以上的文章内容");
			return;
		}
		
		if(StringKit.isNotBlank(cover)){
			cover = Blade.me().webRoot() + File.separator + cover;
		}
		
		boolean flag = postService.save(title, null, user.getUid(), sid, type, is_pub, cover, content);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "文章发布失败");
		}
	}
	
	/**
	 * 发布评论
	 */
	@Route(value = "/comment", method = HttpMethod.POST)
	public void save_comment(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		String pid = request.query("pid");
		String content = request.query("content");
		Long post_uid = request.queryAsLong("post_uid");
		Long cid = request.queryAsLong("cid");
		Long to_uid = request.queryAsLong("to_uid");
		
		if(StringKit.isBlank(pid) || null == post_uid || StringKit.isBlank(content)){
			this.error(response, "参数不能为空");
			return;
		}
		
		if(content.length() > 1000){
			this.error(response, "请输入1000个字符以内的评论");
			return;
		}
		
		String ip = Utils.getIpAddr(request);
		
		boolean flag = commentService.save(pid, cid, user.getUid(), to_uid, post_uid, content, ip);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "评论发布失败");
		}
	}
	
	/**
	 * 收藏文章
	 */
	@Route(value = "/favorite", method = HttpMethod.POST)
	public void favorite(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		String pid = request.query("pid");
		// 1收藏 0取消收藏
		Integer type = request.queryAsInt("type");
		if(null == type || StringKit.isBlank(pid)){
			this.error(response, "收藏失败");
			return;
		}
		boolean flag = false;
		if(type==1){
			flag = favoriteService.favorite(user.getUid(), pid);
		} else{
			flag = favoriteService.delete(user.getUid(), pid);
		}
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "收藏失败");
		}
	}
	
}
