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
import com.writty.model.Favorite;
import com.writty.model.Post;
import com.writty.service.FavoriteService;
import com.writty.service.PostService;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Inject
	private PostService postService;
	
	@Override
	public boolean favorite(Long uid, String pid) {
		Integer time = DateKit.getCurrentUnixTime();
		try {
			AR.update("insert into t_favorite(pid, uid, created) values(?, ?, ?)", pid, uid, time).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Long uid, String pid) {
		if(null == uid || StringKit.isBlank(pid)){
			return false;
		}
		try {
			AR.update("delete from t_favorite where uid = ? and pid = ?", uid, pid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<Map<String, Object>> getMyFavorites(Long uid, Integer page, Integer count) {
		if(null == uid){
			return null;
		}
		if(null == page || page < 1){
			page = 1;
		}
		if(null == count || count < 1){
			count = 10;
		}
		QueryParam queryParam = QueryParam.me();
		queryParam.eq("uid", uid);
		queryParam.page(page, count);
		Page<Favorite> favoritePage = AR.find(queryParam).page(Favorite.class);
		return this.getPageMap(favoritePage);
	}
	
	private Page<Map<String, Object>> getPageMap(Page<Favorite> favoritePage){
		long totalCount = favoritePage.getTotalCount();
		int page = favoritePage.getPage();
		int pageSize = favoritePage.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Favorite> favorites = favoritePage.getResults();
		
		List<Map<String, Object>> favoriteMaps = this.getListMap(favorites);
		result.setResults(favoriteMaps);
		return result;
	}

	private List<Map<String, Object>> getListMap(List<Favorite> favorites) {
		List<Map<String, Object>> favoriteMaps = new ArrayList<Map<String,Object>>();
		if(null != favorites && favorites.size() > 0){
			for(Favorite favorite : favorites){
				Map<String, Object> map = this.getFavoriteDetail(favorite);
				if(null != map && !map.isEmpty()){
					favoriteMaps.add(map);
				}
			}
		}
		return favoriteMaps;
	}

	private Map<String, Object> getFavoriteDetail(Favorite favorite) {
		if(null != favorite){
			String pid = favorite.getPid();
			Post post = postService.getPost(pid);
			if(null == post){
				return null;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", post.getPid());
			map.put("title", post.getTitle());
			map.put("created", post.getCreated());
			map.put("create_date", DateKit.formatDateByUnixTime(post.getCreated().longValue(), "yyyy-MM-dd"));
			map.put("favorite_date", DateKit.formatDateByUnixTime(favorite.getCreated().longValue(), "yyyy-MM-dd"));
			map.put("pid", post.getPid());
			return map;
		}
		return null;
	}
}
