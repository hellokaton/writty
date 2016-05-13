package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Favorite对象
 */
@Table(value = "t_favorite", PK = "id")
public class Favorite implements Serializable {
	
	private static final long serialVersionUID = 2950779981440114650L;
	
	private Long id;
	private String pid;
	private Long uid;
	private Long created;
	
	public Favorite() {
		
	}

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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
	
}