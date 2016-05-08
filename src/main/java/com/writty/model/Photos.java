package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Photos对象
 */
@Table(value = "t_photos", PK = "id")
public class Photos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//相册id
	private Integer aid;
	
	//创建人
	private Integer uid;
	
	//图片标题
	private String title;
	
	//图片地址
	private String photo;
	
	//创建时间
	private Integer created;
	
	public Photos(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}