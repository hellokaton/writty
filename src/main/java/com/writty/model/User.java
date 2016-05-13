package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * User对象
 */
@Table(value = "t_user", PK = "uid")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//用户id
	private Long uid;
	
	//登录名
	private String user_name;
	
	//密码
	private String pass_word;
	
	//昵称
	private String nick_name;
	
	//用户头像
	private String avatar;
	
	//1:系统管理员
	private Integer role_id;
	
	//创建时间
	private Integer created;
	
	//最后修改时间
	private Integer updated;
	
	//最后登录时间
	private Integer logined;
	
	public User(){}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
	public Integer getUpdated() {
		return updated;
	}

	public void setUpdated(Integer updated) {
		this.updated = updated;
	}
	
	public Integer getLogined() {
		return logined;
	}

	public void setLogined(Integer logined) {
		this.logined = logined;
	}
	
}