package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Comment对象
 */
@Table(value = "t_comment", PK = "id")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//文章id
	private String pid;
	
	//评论id
	private Integer cid;
	
	//发布评论的用户
	private Integer uid;
	
	//评论人昵称
	private String nick_name;
	
	//评论人站点
	private String site_url;
	
	//评论人email
	private String email;
	
	//评论人ip
	private String ip;
	
	//评论时间
	private Integer created;
	
	public Comment(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getSite_url() {
		return site_url;
	}

	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}