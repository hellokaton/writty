package com.writty.controller;

import java.util.HashMap;
import java.util.Map;

import com.blade.view.ModelAndView;
import com.blade.web.http.Response;

import blade.kit.json.JSONObject;

public class BaseController {
	
	protected final String ERROR = "error";
	protected final String INFO = "info";
	protected final String SUCCESS = "success";
	protected final String FAILURE = "failure";
	protected final String STATUS = "status";
	protected final String SIGNIN = "signin";
	protected final String NOTNULL = "notnull";
	
	final int count = 20;
	
	public ModelAndView getView(String view){
		return getView(new HashMap<String, Object>(), view);
	}
	
	public ModelAndView getView(Map<String, Object> map, String view){
		return new ModelAndView(map, view + ".html");
	}
	
	public ModelAndView getAdminView(String view){
		return this.getView("/admin/" + view);
	}
	
	public ModelAndView getAdminView(Map<String, Object> map, String view){
		return this.getView(map, "/admin/" + view);
	}
	
	public void success(Response response, Object data){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 200);
		jsonObject.put("data", data);
		response.json(jsonObject.toString());
	}
	
	public void error(Response response, String msg){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 500);
		jsonObject.put("msg", msg);
		response.json(jsonObject.toString());
	}
	
	public void nosignin(Response response){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 401);
		response.json(jsonObject.toString());
	}
	
}
