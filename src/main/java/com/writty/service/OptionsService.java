package com.writty.service;

import java.util.Map;

import com.writty.model.Options;

public interface OptionsService {
	
	Options getOptions(String okey);
	
	Map<String, Object> getSystemInfo();
	
	boolean delete(String okey);

	boolean update(String site_title, String site_keywords, String site_description);
		
}
