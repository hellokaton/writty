package com.writty.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.writty.model.User;
import com.writty.service.OpenService;
import com.writty.service.UserService;

import blade.kit.DateKit;
import blade.kit.EncrypKit;
import blade.kit.StringKit;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private OpenService openService;
	
	@Override
	public User getUser(Long uid) {
		return AR.findById(User.class, uid);
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
	public boolean delete(Long uid) {
		if(null != uid){
			AR.update("delete from ").executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public User signin(String userName, String passWord) {
		if(StringKit.isNotBlank(userName) && StringKit.isNotBlank(passWord)){
			String pwd = EncrypKit.md5(userName + passWord);
			User user = AR.find("select * from t_user where user_name = ? and pass_word = ?", userName, pwd).first(User.class);
			return user;
		}
		return null;
	}

	@Override
	public User saveGithubUser(String login, String name, String avatar_url, Long open_id) {
		if(StringKit.isNotBlank(login) && null != open_id){
			try {
				Integer time = DateKit.getCurrentUnixTime();
				Long uid = (Long) AR
						.update("insert into t_user(user_name, nick_name, avatar, created, updated) values(?, ?, ?, ?, ?)",
								login, name, avatar_url, time, time)
						.key();
				openService.save(open_id, uid);
				
				return this.getUser(uid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean updateRole(Long uid, Integer role_id) {
		try {
			AR.update("update t_user set role_id = ? where uid = ?", role_id, uid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePwd(Long uid, String newpwd) {
		if(null != uid && null != newpwd){
			try {
				AR.update("update t_user set pass_word = ? where uid = ?", newpwd, uid).executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
		
}
