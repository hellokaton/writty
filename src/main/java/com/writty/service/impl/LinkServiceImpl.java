package com.writty.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.writty.model.Link;
import com.writty.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Override
	public Link getLink(Integer id) {
		return AR.findById(Link.class, id);
	}
	
	@Override
	public List<Link> getLinkList() {
		return AR.find("").list(Link.class);
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
