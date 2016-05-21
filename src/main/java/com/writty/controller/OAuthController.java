package com.writty.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.Constant;
import com.writty.kit.SessionKit;
import com.writty.model.Open;
import com.writty.model.User;
import com.writty.service.OpenService;
import com.writty.service.UserService;

import blade.kit.StringKit;
import blade.kit.http.HttpRequest;
import blade.kit.json.JSONKit;
import blade.kit.json.JSONObject;
import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;

@Path("/github/")
public class OAuthController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthController.class);
	
	@Inject
	private UserService userService;
	
	@Inject
	private OpenService openService;
	
	/**
	 * github回调
	 */
	@Route(value = "/")
	public void github(Request request, Response response){
		try {
			String url = "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&state=%s";
			String redirect_uri = URLEncoder.encode(Constant.GITHUB_REDIRECT_URL, "utf-8");
			response.redirect(String.format(url, Constant.GITHUB_CLIENT_ID, redirect_uri, StringKit.getRandomNumber(15)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * github回调
	 */
	@Route(value = "callback")
	public ModelAndView githubCall(Request request, Response response){
		
		String code = request.query("code");
		
		if(StringKit.isNotBlank(code)){
			LOGGER.info("code = {}", code);
			
			String body = HttpRequest.post("https://github.com/login/oauth/access_token", true,
					"client_id" , Constant.GITHUB_CLIENT_ID,
					"client_secret" , Constant.GITHUB_CLIENT_SECRET,
					"code", code)
					.accept("application/json")
					.readTimeout(10 * 1000)
					.trustAllCerts().trustAllHosts().body();
			
			LOGGER.info("body = {}", body);
			
			JSONObject result = JSONKit.parseObject(body);
		 	String access_token = result.getString("access_token");
		 	
		 	String body_ = HttpRequest.get("https://api.github.com/user?access_token=" + access_token).body();
		 	
		 	System.out.println("body = " + body_);
		 	
		 	JSONObject githubUser = JSONKit.parseObject(body_);
		 	Long open_id = githubUser.getLong("id");
		 	String login = githubUser.getString("login");
		 	String name = githubUser.getString("name");
		 	String email = githubUser.getString("email");
		 	String avatar_url = githubUser.getString("avatar_url");
		 	
		 	User user = null;
		 	
		 	// 判断用户是否已经绑定
		 	Open open = openService.getOpenByOpenId(open_id);
		 	if(null == open){
		 		Map<String, String> githubInfo = new HashMap<String, String>(3);
		 		githubInfo.put("login_name", login);
		 		githubInfo.put("open_id", open_id.toString());
		 		user = userService.saveGithubUser(login, name, email, avatar_url, open_id);
		 	} else {
		 		user = userService.getUser(open.getUid());
			}
		 	if(null != user){
		 		SessionKit.setLoginUser(request.session(), user);
				response.go("/");
		 	}
		} else {
			request.attribute(this.ERROR, "请求发生异常");
			return this.getView("info");
		}
	 	return null;
	}
	
}
