package com.writty.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.writty.model.Special;

public interface SpecialService {
	
	Special getSpecial(Long id);
	
	Integer getSpecialCount(String title, String slug);
	
	Map<String, Object> getSpecialMap(Special special, Long id);
	
	List<Special> getSpecialList(QueryParam where);
	
	Page<Map<String, Object>> getPageListMap(String title, Integer is_pub, Integer page, Integer count, String orderby);
	
	List<Map<String, Object>> getRandomList();
	
	boolean save(String title, String slug, String cover, String description);
	
	boolean delete(Long id);
	
	boolean update(Long id, String title, String slug, String cover, String description);
		
}
