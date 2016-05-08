package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Album;
import com.writty.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Override
	public Album getAlbum(Integer id) {
		return AR.findById(Album.class, id);
	}
	
	@Override
	public Album getAlbum(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Album.class);
		}
		return null;
	}
	
	@Override
	public List<Album> getAlbumList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Album.class);
		}
		return null;
	}
	
	@Override
	public Page<Album> getPageList(QueryParam where) {
		Page<Album> pageAlbum = AR.find(where).page(Album.class);
		return pageAlbum;
	}
	
	@Override
	public boolean save( Integer uid, String title, String cover, String passWord, Integer photoCount, Integer created ) {
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
