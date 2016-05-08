package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Link;
import com.writty.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Override
	public Link getLink(Integer id) {
		return AR.findById(Link.class, id);
	}
	
	@Override
	public Link getLink(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Link.class);
		}
		return null;
	}
	
	@Override
	public List<Link> getLinkList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Link.class);
		}
		return null;
	}
	
	@Override
	public Page<Link> getPageList(QueryParam where) {
		Page<Link> pageLink = AR.find(where).page(Link.class);
		return pageLink;
	}
	
	@Override
	public boolean save( String title, String url, Integer isNew, Integer displayOrder ) {
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
