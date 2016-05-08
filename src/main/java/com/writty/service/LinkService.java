package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Link;

public interface LinkService {
	
	public Link getLink(Integer id);
	
	public Link getLink(QueryParam where);
	
	public List<Link> getLinkList(QueryParam where);
	
	public Page<Link> getPageList(QueryParam where);
	
	public boolean save( String title, String url, Integer isNew, Integer displayOrder );
	
	public boolean delete(Integer id);
		
}
