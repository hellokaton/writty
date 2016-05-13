package com.writty.controller.api;

import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.writty.controller.BaseController;
import com.writty.service.SpecialService;

@Path("/api")
public class IndexController extends BaseController {
	
	@Inject
	private SpecialService specialService;
	
	@Route(value = "/specials/random", method = HttpMethod.GET)
	public void getRandomSpecials(Request request, Response response){
		List<Map<String, Object>> specials = specialService.getRandomList();
		response.json(this.JSONRes("specials", specials));
	}
	
}
