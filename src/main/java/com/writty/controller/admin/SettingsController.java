package com.writty.controller.admin;

import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.AR;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.Constant;
import com.writty.controller.BaseController;
import com.writty.service.OptionsService;

import blade.kit.StringKit;

/**
 * 系统设置
 */
@Path("/admin/")
public class SettingsController extends BaseController {
	
	@Inject
	private OptionsService optionsService;
	
	/**
	 * 系统设置页面
	 */
	@Route(value = "settings", method = HttpMethod.GET)
	public ModelAndView show_settings(Request request, Response response){
		Map<String, Object> settings = optionsService.getSystemInfo();
		request.attribute("settings", settings);
		return this.getAdminView("settings");
	}
	
	/**
	 * 保存系统设置
	 */
	@Route(value = "settings", method = HttpMethod.POST)
	public void save_settings(Request request, Response response){
		String site_title = request.query("site_title");
		String site_keywords = request.query("site_keywords");
		String site_description = request.query("site_description");
		optionsService.update(site_title, site_keywords, site_description);
		Constant.SYS_INFO = optionsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
		this.success(response, "");
	}
	
	/**
	 * 系统工具
	 */
	@Route(value = "tools", method = HttpMethod.GET)
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
