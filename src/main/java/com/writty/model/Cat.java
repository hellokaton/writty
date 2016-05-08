package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Cat对象
 */
@Table(value = "t_cat", PK = "id")
public class Cat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//分类名称
	private String title;
	
	//分类显示名
	private String slug;
	
	//文章数
	private Integer post_count;
	
	//分类创建时间
	private Integer created;
	
	public Cat(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public Integer getPost_count() {
		return post_count;
	}

	public void setPost_count(Integer post_count) {
		this.post_count = post_count;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}