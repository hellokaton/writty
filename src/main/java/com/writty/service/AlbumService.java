package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Album;

public interface AlbumService {
	
	public Album getAlbum(Integer id);
	
	public Album getAlbum(QueryParam where);
	
	public List<Album> getAlbumList(QueryParam where);
	
	public Page<Album> getPageList(QueryParam where);
	
	public boolean save( Integer uid, String title, String cover, String passWord, Integer photoCount, Integer created );
	
	public boolean delete(Integer id);
		
}
