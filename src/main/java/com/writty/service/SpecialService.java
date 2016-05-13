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
	
	Page<Map<String, Object>> getPageListMap(String title, Integer page, Integer count);
	
	boolean save(String title, String slug, String cover, String description);
	
	boolean delete(Integer id);
	
	boolean update(Long id, String title, String slug, String cover, String description);
		
}
