package com.writty.service;

import java.util.Map;

import com.blade.jdbc.Page;

public interface FavoriteService {

	boolean favorite(Long uid, String pid);
	
	boolean delete(Long uid, String  pid);
	
	Long getFavoriteCount(String pid);
	
	Page<Map<String, Object>> getMyFavorites(Long uid, Integer page, Integer count);
	
}
