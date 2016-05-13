package com.writty.interceptor;

import com.blade.interceptor.Interceptor;
import com.blade.interceptor.annotation.Intercept;
import com.blade.ioc.annotation.Inject;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.verify.CSRFTokenManager;
import com.writty.Constant;
import com.writty.kit.SessionKit;
import com.writty.kit.Utils;
import com.writty.model.User;
import com.writty.service.UserService;

import blade.kit.StringKit;
import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;

@Intercept
public class BaseInterceptor implements Interceptor {
	
	private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
	
	@Inject
	private UserService userService;
	
	@Override
	public boolean before(Request request, Response response) {
		
		LOGGE.info("UA >>> " + request.userAgent());
		LOGGE.info("用户访问地址 >>> " + request.raw().getRequestURI() + ", 来路地址  >>> " + Utils.getIpAddr(request));
		
		User user = SessionKit.getLoginUser();
		
		if(null == user){
			String val = SessionKit.getCookie(request, Constant.USER_IN_COOKIE);
			if(null != val){
				if(StringKit.isNumber(val)){
					Long uid = Long.valueOf(val);
					user = userService.getUser(uid);
					SessionKit.setLoginUser(request.session(), user);
				} else {
					response.removeCookie(Constant.USER_IN_COOKIE);
				}
			}
		}
		
		String uri = request.uri();
		if(uri.indexOf("/admin/") != -1){
			if(null == user || user.getRole_id() != 1){
				response.go("/signin");
				return false;
			}
		}
		
		if(request.method().equals("POST")){
			String referer = request.header("Referer");
			if(StringKit.isBlank(referer) || !referer.startsWith(Constant.SITE_URL)){
				response.go("/");
				return false;
			}
			/*if(request.isAjax() && !CSRFTokenManager.verify(request, response)){
				response.text("CSRF ERROR");
	            return false;
	        }*/
		}
		CSRFTokenManager.createNewToken(request, response);
		
		return true;
	}
	

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
