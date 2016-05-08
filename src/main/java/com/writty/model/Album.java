package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Album对象
 */
@Table(value = "t_album", PK = "id")
public class Album implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//相册id
	private Integer id;
	
	//创建人uid
	private Integer uid;
	
	//相册名称
	private String title;
	
	//相册封面
	private String cover;
	
	//相册密码
	private String pass_word;
	
	//图片数量
	private Integer photo_count;
	
	//创建时间
	private Integer created;
	
	public Album(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	
	public Integer getPhoto_count() {
		return photo_count;
	}

	public void setPhoto_count(Integer photo_count) {
		this.photo_count = photo_count;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}