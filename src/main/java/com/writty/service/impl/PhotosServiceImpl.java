package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Photos;
import com.writty.service.PhotosService;

@Service
public class PhotosServiceImpl implements PhotosService {
	
	@Override
	public Photos getPhotos(Integer id) {
		return AR.findById(Photos.class, id);
	}
	
	@Override
	public Photos getPhotos(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Photos.class);
		}
		return null;
	}
	
	@Override
	public List<Photos> getPhotosList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Photos.class);
		}
		return null;
	}
	
	@Override
	public Page<Photos> getPageList(QueryParam where) {
		Page<Photos> pagePhotos = AR.find(where).page(Photos.class);
		return pagePhotos;
	}
	
	@Override
	public boolean save( Integer aid, Integer uid, String title, String photo, Integer created ) {
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
