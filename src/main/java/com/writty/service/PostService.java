package com.writty.service;

import java.util.Map;

import com.blade.jdbc.Page;
import com.writty.model.Post;

public interface PostService {
	
	Post getPost(String pid);
	
	Map<String, Object> getPostDetail(Post post, String pid);
	
	Page<Map<String, Object>> getPageListMap(String title, Integer page, Integer count);
	
	boolean save(String title, String slug, Long uid, Long sid, Integer is_pub, String cover, String content);
	
	boolean delete(String pid);
		
}
