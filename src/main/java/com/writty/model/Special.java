package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Special对象
 */
@Table(value = "t_special", PK = "id")
public class Special implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	//分类名称
	private String title;
	
	//分类显示名
	private String slug;
	
	//分类封面
	private String cover;
	
	//分类介绍
	private String description;
	
	//文章数
	private Integer post_count;
	
	//关注数
	private Integer follow_count;
	
	//分类创建时间
	private Integer created;
	
	public Special(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPost_count() {
		return post_count;
	}

	public void setPost_count(Integer post_count) {
		this.post_count = post_count;
	}
	
	public Integer getFollow_count() {
		return follow_count;
	}

	public void setFollow_count(Integer follow_count) {
		this.follow_count = follow_count;
	}
	
	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}
	
}