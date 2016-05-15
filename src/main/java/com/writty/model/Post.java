package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Post对象
 */
@Table(value = "t_post", PK = "pid")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//文章uuid
	private String pid;
	
	//文章标题
	private String title;
	
	//自定义文章显示名
	private String slug;
	
	//文章发布人
	private Long uid;
	
	//所属专栏id
	private Long sid;
	
	//文章封面图
	private String cover;
	
	//文章内容
	private String content;
	
	private Integer type;
	
	//文章评论数
	private Integer comments;
	
	//文章发布状态
	private Integer is_pub;
	
	//文章发布时间
	private Integer created;
	
	//最后更新时间
	private Integer updated;
	
	public Post(){}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
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

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getIs_pub() {
		return is_pub;
	}
	
	public void setIs_pub(Integer is_pub) {
		this.is_pub = is_pub;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}