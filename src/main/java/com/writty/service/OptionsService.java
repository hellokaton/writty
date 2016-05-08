package com.writty.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Options;

public interface OptionsService {
	
	public Options getOptions(String okey);
	
	Map<String, Object> getSystemInfo();
	
	public Options getOptions(QueryParam where);
	
	public List<Options> getOptionsList(QueryParam where);
	
	public Page<Options> getPageList(QueryParam where);
	
	public boolean save( String ovalue );
	
	public boolean delete(String okey);
		
}
