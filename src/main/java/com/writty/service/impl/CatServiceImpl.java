package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Cat;
import com.writty.service.CatService;

@Service
public class CatServiceImpl implements CatService {
	
	@Override
	public Cat getCat(Integer id) {
		return AR.findById(Cat.class, id);
	}
	
	@Override
	public Cat getCat(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Cat.class);
		}
		return null;
	}
	
	@Override
	public List<Cat> getCatList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Cat.class);
		}
		return null;
	}
	
	@Override
	public Page<Cat> getPageList(QueryParam where) {
		Page<Cat> pageCat = AR.find(where).page(Cat.class);
		return pageCat;
	}
	
	@Override
	public boolean save( String title, String slug, Integer postCount, Integer created ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(null != id){
			AR.update("delete from ").executeUpdate();
			return true;
		}
		return false;
	}
		
}
