package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Cat;

public interface CatService {
	
	public Cat getCat(Integer id);
	
	public Cat getCat(QueryParam where);
	
	public List<Cat> getCatList(QueryParam where);
	
	public Page<Cat> getPageList(QueryParam where);
	
	public boolean save( String title, String slug, Integer postCount, Integer created );
	
	public boolean delete(Integer id);
		
}
