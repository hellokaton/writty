package com.writty.controller;

import java.io.File;
import java.util.Date;

import com.blade.Blade;
import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.multipart.FileItem;
import com.writty.Constant;
import com.writty.kit.SessionKit;
import com.writty.kit.Utils;
import com.writty.model.User;
import com.writty.service.UserService;

import blade.kit.DateKit;
import blade.kit.EncrypKit;
import blade.kit.FileKit;
import blade.kit.PatternKit;
import blade.kit.StringKit;
import blade.kit.json.JSONObject;

@Path("/")
public class UserController extends BaseController {
	
	@Inject
	private UserService userService;
	
	/**
	 * 个人设置页面
	 */
	@Route(value = "/settings", method = HttpMethod.GET)
	public ModelAndView show_settings(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		return this.getView("settings");
	}
	
	/**
	 * 申请编辑页面
	 */
	@Route(value = "/request", method = HttpMethod.GET)
	public ModelAndView show_request(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		return this.getView("request");
	}
	
	/**
	 * 申请编辑
	 */
	@Route(value = "/request", method = HttpMethod.POST)
	public ModelAndView requestEditor(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		
		return this.getView("request");
	}
	
	/**
	 * 修改密码
	 */
	@Route(value = "/reset_pwd", method = HttpMethod.GET)
	public void reset_pwd(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return;
		}
		String pwd = request.query("pwd");
		if(StringKit.isBlank(pwd) || pwd.length() < 6){
			this.error(response, "请输入6位以上密码");
			return;
		}
		String newpwd = EncrypKit.md5(user.getUser_name() + pwd);
		userService.updatePwd(user.getUid(), newpwd);
	}
	
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
		return this.getView("write");
	}
	
	/**
	 * 上传头像
	 */
	@Route(value = "/uploadimg", method = HttpMethod.POST)
	public void uploadimg(Request request, Response response){
		User user = SessionKit.getLoginUser();
		if(null == user){
			return;
		}
		
		FileItem[] fileItems = request.files();
		if(null != fileItems && fileItems.length > 0){
			
			FileItem fileItem = fileItems[0];
			
			String type = request.query("type");
			String suffix = FileKit.getExtension(fileItem.getFileName());
			if(StringKit.isNotBlank(suffix)){
				suffix = "." + suffix;
			}
			if(!PatternKit.isImage(suffix)){
				return;
			}
			
			if(null == type){
				type = "temp";
			}
			
			String saveName = DateKit.dateFormat(new Date(), "yyyyMMddHHmmssSSS")  + "_" + StringKit.getRandomChar(10) + suffix;
			File file = new File(Blade.me().webRoot() + File.separator + Constant.UPLOAD_FOLDER + File.separator + saveName);
			
			try {
				
				Utils.copyFileUsingFileChannels(fileItem.getFile(), file);
				
				String filePath = Constant.UPLOAD_FOLDER + "/" + saveName;
				
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("savekey", filePath);
				res.put("savepath", filePath);
				res.put("url", Constant.SITE_URL + "/" + filePath);
				
				response.json(res.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
