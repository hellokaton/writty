package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Open对象
 */
@Table(value = "t_open", PK = "id")
public class Open implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Long open_id;
	
	private Long uid;
	
	private Integer created;
	
	public Open(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getOpen_id() {
		return open_id;
	}

	public void setOpen_id(Long open_id) {
		this.open_id = open_id;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}