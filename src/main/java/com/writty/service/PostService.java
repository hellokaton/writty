package com.writty.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.writty.model.Post;

public interface PostService {
	
	Post getPost(String pid);
	
	Map<String, Object> getPostDetail(Post post, String pid);
	
	List<Post> getList(Long uid, Integer is_pub, String title, String orderby);
	
	List<Map<String, Object>> getListMap(Long uid, Long sid, Integer is_pub, String title, String orderby);
	
	Page<Map<String, Object>> getPageListMap(Long uid, Long sid, Integer is_pub, String title, Integer page, Integer count, String orderby);
	
	boolean save(String title, String slug, Long uid, Long sid, Integer type, Integer is_pub, String cover, String content);
	
	boolean delete(String pid);

	boolean audit(String pid);
		
}
