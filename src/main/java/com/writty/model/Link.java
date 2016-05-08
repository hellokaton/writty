package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Link对象
 */
@Table(value = "t_link", PK = "id")
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//链接名称
	private String title;
	
	//链接地址
	private String url;
	
	//是否新窗口打开
	private Integer is_new;
	
	//排序
	private Integer display_order;
	
	public Link(){}
	
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getIs_new() {
		return is_new;
	}

	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}
	
	public Integer getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(Integer display_order) {
		this.display_order = display_order;
	}
	
}