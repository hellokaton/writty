package com.writty;

import java.util.Map;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.DB;
import com.blade.jdbc.cache.memory.FIFOCache;
import com.blade.web.verify.CSRFConfig;
import com.blade.web.verify.CSRFTokenManager;
import com.bladejava.view.template.JetbrickTemplateEngine;
import com.writty.ext.Funcs;
import com.writty.ext.Methods;
import com.writty.service.OptionsService;

import blade.kit.AES;
import blade.kit.StringKit;
import jetbrick.template.resolver.GlobalResolver;

public class App extends Bootstrap {
	
	@Inject
	private OptionsService optionsService;
	
	@Override
	public void init(Blade blade) {
		// 模板引擎
		JetbrickTemplateEngine jetbrickTemplateEngine = new JetbrickTemplateEngine(BladeWebContext.servletContext());
		
		GlobalResolver resolver = jetbrickTemplateEngine.getJetEngine().getGlobalResolver();
		resolver.registerFunctions(Funcs.class);
		resolver.registerMethods(Methods.class);
		Constant.VIEW_CONTEXT = jetbrickTemplateEngine.getJetEngine().getGlobalContext();
		blade.viewEngin(jetbrickTemplateEngine);
		
		// 配置数据库
		DB.open(blade.config().get("jdbc.driver"), 
				blade.config().get("jdbc.url"), 
				blade.config().get("jdbc.user"), 
				blade.config().get("jdbc.pass"), true);
		
		// 开启数据库缓存
		if(blade.config().getAsBoolean("writty.db_cahce")){
			DB.setCache(new FIFOCache());
		}
		
		// 初始化配置
		Constant.SITE_URL = blade.config().get("writty.site_url");
		Constant.APP_VERSION = blade.config().get("writty.version");
		AES.setKey(blade.config().get("writty.aes_salt"));
		Constant.CDN_URL = Constant.SITE_URL;
		if(StringKit.isNotBlank(blade.config().get("qiniu.cdn"))){
			Constant.CDN_URL = blade.config().get("qiniu.cdn");
		}
		
		// csrf 防御
		CSRFConfig conf = new CSRFConfig();
		conf.setSecret(blade.config().get("writty.aes_salt"));
		conf.setSetHeader(true);
		CSRFTokenManager.config(conf);
		
		// github授权key
		Constant.GITHUB_CLIENT_ID = blade.config().get("github.CLIENT_ID");
		Constant.GITHUB_CLIENT_SECRET = blade.config().get("github.CLIENT_SECRET");
		Constant.GITHUB_REDIRECT_URL = blade.config().get("github.REDIRECT_URL");
		
	}
	
	@Override
	public void contextInitialized(Blade blade) {
		Constant.SYS_INFO = optionsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set("base", BladeWebContext.servletContext().getContextPath());
		Constant.VIEW_CONTEXT.set("version", Constant.APP_VERSION);
		Constant.VIEW_CONTEXT.set("cdn", Constant.CDN_URL);
		Constant.VIEW_CONTEXT.set(Map.class, "sys_info", Constant.SYS_INFO);
		Constant.VIEW_CONTEXT.set(String.class, "site_url", Constant.SITE_URL);
	}
}
