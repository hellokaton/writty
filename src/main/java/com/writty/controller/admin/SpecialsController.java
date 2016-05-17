package com.writty.controller.admin;

import java.io.File;
import java.util.Map;

import com.blade.Blade;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.service.SpecialService;

import blade.kit.StringKit;

/**
 * 专栏管理
 */
@Path("/admin/")
public class SpecialsController extends BaseController {
	
	@Inject
	private SpecialService specialService;
	
	/**
	 * 专栏管理
	 */
	@Route(value = "/specials", method = HttpMethod.GET)
	public ModelAndView show_specials(Request request, Response response){
		Integer page = request.queryAsInt("p");
		String title = request.query("title");
		if(null == page || page < 1){
			page = 1;
		}
		
		if(StringKit.isNotBlank(title)){
			request.attribute("title", title);
		}
		
		Page<Map<String, Object>> specialPage = specialService.getPageListMap(title, page, 15);
		request.attribute("specialPage", specialPage);
		
		return this.getAdminView("specials");
	}
	
	/**
	 * 发布专栏页面
	 */
	@Route(value = "/specials/add", method = HttpMethod.GET)
	public ModelAndView addSpecial(Request request, Response response){
		return this.getAdminView("add_special");
	}
	
	/**
	 * 编辑专栏页面
	 */
	@Route(value = "/specials/:id", method = HttpMethod.GET)
	public ModelAndView editSpecial(@PathVariable(value="id") Long id, Request request, Response response){
		Map<String, Object> special = specialService.getSpecialMap(null, id);
		request.attribute("special", special);
		return this.getAdminView("edit_special");
	}
	
	/**
	 * 检查专栏是否已经存在
	 */
	@Route(value = "/specials/check", method = HttpMethod.GET)
	public void checkSpecial(Request request, Response response){
		String title = request.query("title");
		String slug = request.query("slug");
		
		if(StringKit.isBlank(title) && StringKit.isBlank(slug)){
			return;
		}
		
		Integer count = 0;
		if(StringKit.isNotBlank(title)){
			count = specialService.getSpecialCount(title, null);
		}
		if(StringKit.isNotBlank(slug)){
			count = specialService.getSpecialCount(null, slug);
		}
		this.success(response, count + "");
	}
	
	/**
	 * 发布专栏
	 */
	@Route(value = "/specials", method = HttpMethod.POST)
	public void saveSpecial(Request request, Response response){
		
		String title = request.query("title");
		String slug = request.query("slug");
		String cover = request.query("cover");
		String description = request.query("description");
		
		if(StringKit.isBlank(title) ||
				StringKit.isBlank(slug) ||
				StringKit.isBlank(cover) ||
				StringKit.isBlank(description)){
			this.error(response, "参数不能为空");
			return;
		}
		
		if(title.length() > 20){
			this.error(response, "标题应控制在20个字符以内");
			return;
		}
		
		if(description.length() > 300){
			this.error(response, "描述应控制在300个字符以内");
			return;
		}
		
		String cover_path = Blade.me().webRoot() + File.separator + cover;
		boolean flag = specialService.save(title, slug, cover_path, description);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "专栏创建失败");
		}
	}
	
	/**
	 * 修改专栏
	 */
	@Route(value = "/specials/update", method = HttpMethod.POST)
	public void updateSpecial(Request request, Response response){
		Long id = request.queryAsLong("id");
		String title = request.query("title");
		String slug = request.query("slug");
		String cover = request.query("cover");
		String description = request.query("description");
		if(StringKit.isNotBlank(cover)){
			cover = Blade.me().webRoot() + File.separator + cover;
		}
		boolean flag = specialService.update(id, title, slug, cover, description);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "专栏修改失败");
		}
	}
	
	
}
