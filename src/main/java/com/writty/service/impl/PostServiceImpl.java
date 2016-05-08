package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Post;
import com.writty.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Override
	public Post getPost(String pid) {
		return AR.findById(Post.class, pid);
	}
	
	@Override
	public Post getPost(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Post.class);
		}
		return null;
	}
	
	@Override
	public List<Post> getPostList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Post.class);
		}
		return null;
	}
	
	@Override
	public Page<Post> getPageList(QueryParam where) {
		Page<Post> pagePost = AR.find(where).page(Post.class);
		return pagePost;
	}
	
	@Override
	public boolean save( String title, String slug, Integer uid, String cover, String content, Integer views, Integer comments, Integer created, Integer isPage, Integer updated ) {
		return false;
	}
	
	@Override
	public boolean delete(String pid) {
		if(null != pid){
			AR.update("delete from ").executeUpdate();
			return true;
		}
		return false;
	}
		
}
