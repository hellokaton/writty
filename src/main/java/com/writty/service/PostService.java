package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Post;

public interface PostService {
	
	public Post getPost(String pid);
	
	public Post getPost(QueryParam where);
	
	public List<Post> getPostList(QueryParam where);
	
	public Page<Post> getPageList(QueryParam where);
	
	public boolean save( String title, String slug, Integer uid, String cover, String content, Integer views, Integer comments, Integer created, Integer isPage, Integer updated );
	
	public boolean delete(String pid);
		
}
