package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.User;

public interface UserService {
	
	public User getUser(Integer uid);
	
	public User getUser(QueryParam where);
	
	public List<User> getUserList(QueryParam where);
	
	public Page<User> getPageList(QueryParam where);
	
	public boolean save( String userName, String passWord, String nickName, String avatar, Integer roleId, Integer created, Integer updated, Integer logined );
	
	public boolean delete(Integer uid);
		
}
