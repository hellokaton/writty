package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.User;
import com.writty.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public User getUser(Integer uid) {
		return AR.findById(User.class, uid);
	}
	
	@Override
	public User getUser(QueryParam where) {
		if(null != where){
			return AR.find(where).first(User.class);
		}
		return null;
	}
	
	@Override
	public List<User> getUserList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(User.class);
		}
		return null;
	}
	
	@Override
	public Page<User> getPageList(QueryParam where) {
		Page<User> pageUser = AR.find(where).page(User.class);
		return pageUser;
	}
	
	@Override
	public boolean save( String userName, String passWord, String nickName, String avatar, Integer roleId, Integer created, Integer updated, Integer logined ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer uid) {
		if(null != uid){
			AR.update("delete from ").executeUpdate();
			return true;
		}
		return false;
	}
		
}
