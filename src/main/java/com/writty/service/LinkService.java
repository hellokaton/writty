package com.writty.service;

import java.util.List;

import com.writty.model.Link;

public interface LinkService {
	
	Link getLink(Integer id);
	
	List<Link> getLinkList();
	
	boolean save( String title, String url, Integer isNew, Integer displayOrder );
	
	boolean delete(Integer id);
		
}
