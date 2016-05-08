package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Photos;

public interface PhotosService {
	
	public Photos getPhotos(Integer id);
	
	public Photos getPhotos(QueryParam where);
	
	public List<Photos> getPhotosList(QueryParam where);
	
	public Page<Photos> getPageList(QueryParam where);
	
	public boolean save( Integer aid, Integer uid, String title, String photo, Integer created );
	
	public boolean delete(Integer id);
		
}
