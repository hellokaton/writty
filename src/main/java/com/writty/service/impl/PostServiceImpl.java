package com.writty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.writty.model.Post;
import com.writty.model.User;
import com.writty.service.PostService;
import com.writty.service.UserService;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class PostServiceImpl implements PostService {
	
	@Inject
	private UserService userService;
	
	@Override
	public Post getPost(String pid) {
		return AR.findById(Post.class, pid);
	}
	
	@Override
	public Page<Map<String, Object>> getPageListMap(String title, Integer page, Integer count) {
		QueryParam up = QueryParam.me();
		if(StringKit.isNotBlank(title)){
			up.like("title", "%"+title+"%");
		}
		up.orderby("created desc").page(page, count);
		Page<Post> postPage = AR.find(up).page(Post.class);
		return this.getPageListMap(postPage);
	}
	
	private Page<Map<String, Object>> getPageListMap(Page<Post> postPage) {
		long totalCount = postPage.getTotalCount();
		int page = postPage.getPage();
		int pageSize = postPage.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Post> posts = postPage.getResults();
		
		List<Map<String, Object>> postMaps = this.getListMap(posts);
		
		result.setResults(postMaps);
		return result;
	}

	private List<Map<String, Object>> getListMap(List<Post> posts) {
		List<Map<String, Object>> postMaps = new ArrayList<Map<String,Object>>();
		if(null != posts && posts.size() > 0){
			for(Post post : posts){
				Map<String, Object> map = this.getPostDetail(post, null);
				if(null != map && !map.isEmpty()){
					postMaps.add(map);
				}
			}
		}
		return postMaps;
	}

	@Override
	public Map<String, Object> getPostDetail(Post post, String pid) {
		if(null == post){
			post = this.getPost(pid);
		}
		if(null != post){
			Long uid = post.getUid();
			User user = userService.getUser(uid);
			if(null == user){
				return null;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", post.getPid());
			map.put("title", post.getTitle());
			map.put("created", post.getCreated());
			map.put("create_date", DateKit.formatDateByUnixTime(post.getCreated().longValue(), "yyyy-MM-dd"));
			map.put("user_name", user.getUser_name());
			map.put("publish_user", user.getNick_name());
			map.put("user_avatar", user.getAvatar());
			map.put("pid", post.getPid());
			return map;
		}
		return null;
	}

	@Override
	public boolean save( String title, String slug, Long uid, Long sid, Integer is_pub, String cover, String content) {
		
		try {
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_post(title, slug, uid, sid, cover, content, is_pub, created, updated) values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
					title, slug, uid,  sid, cover, content, is_pub, time, time).executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
