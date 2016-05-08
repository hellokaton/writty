package com.writty.kit;

import com.blade.context.BladeWebContext;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.http.wrapper.Session;
import com.writty.Constant;
import com.writty.model.User;

import blade.kit.AES;
import blade.kit.StringKit;

public class SessionKit {

	public static void set(Session session, String name, Object value){
		if(null != session && StringKit.isNotBlank(name) && null != value){
			removeUser(session);
			session.attribute(name, value);
		}
	}
	
	public static <T> T get(Session session, String name){
		if(null != session && StringKit.isNotBlank(name)){
			return session.attribute(name);
		}
		return null;
	}
	
	public static void setLoginUser(Session session, User login_user){
		if(null != session && null != login_user){
			removeUser(session);
			session.attribute(Constant.LOGIN_SESSION_KEY, login_user);
		}
	}
	
	public static void removeUser(Session session){
		session.removeAttribute(Constant.LOGIN_SESSION_KEY);
	}
	
	public static User getLoginUser() {
		Session session = BladeWebContext.session();
		if(null == session){
			return null;
		}
		User user = session.attribute(Constant.LOGIN_SESSION_KEY);
		return user;
	}
	
	private static final int one_month = 30*24*60*60;
	
	public static void setCookie(Response response, String cookieName, Long uid) {
		if(null != response && StringKit.isNotBlank(cookieName) && null != uid){
			String val = AES.encrypt(uid+"");
			boolean isSSL = Constant.SITE_URL.startsWith("https");
			String path = BladeWebContext.servletContext().getContextPath() + "/";
			response.cookie(path, cookieName, val, one_month, isSSL);
		}
	}
	
	public static void setCookie(Response response, String cookieName, String value) {
		if(null != response && StringKit.isNotBlank(cookieName) && StringKit.isNotBlank(value)){
			String val = AES.encrypt(value);
			boolean isSSL = Constant.SITE_URL.startsWith("https");
			response.removeCookie(cookieName);
			String path = BladeWebContext.servletContext().getContextPath() + "/";
			response.cookie(path, cookieName, val, 604800, isSSL);
		}
	}
	
	public static String getCookie(Request request, String cookieName) {
		if(null != request && StringKit.isNotBlank(cookieName)){
			String val = request.cookie(cookieName);
			if(StringKit.isNotBlank(val)){
				try {
					return AES.decrypt(val);
				} catch (Exception e) {
				}
				return "";
			}
		}
		return null;
	}

	public static void removeCookie(Response response) {
		response.removeCookie(Constant.USER_IN_COOKIE);
		response.removeCookie(Constant.JC_REFERRER_COOKIE);
	}
	
}
