package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Comment对象
 */
@Table(value = "t_comment", PK = "id")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	//文章id
	private String pid;
	
	//评论id
	private Long cid;
	
	//发布评论的用户
	private Long uid;
	
	private Long to_uid;
	
	private String content;
	
	//评论人ip
	private String ip;
	
	//评论时间
	private Integer created;
	
	public Comment(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getTo_uid() {
		return to_uid;
	}

	public void setTo_uid(Long to_uid) {
		this.to_uid = to_uid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}