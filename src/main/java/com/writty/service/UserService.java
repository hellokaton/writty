package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.User;

public interface UserService {
	
	User getUser(Long uid);
	
	List<User> getUserList(QueryParam where);
	
	Page<User> getPageList(QueryParam where);
	
	boolean delete(Long uid);

	User signin(String userName, String passWord);

	User saveGithubUser(String login, String name, String email, String avatar_url, Long open_id);

	boolean updateRole(Long uid, Integer role_id);

	boolean updatePwd(Long uid, String newpwd);
		
}
