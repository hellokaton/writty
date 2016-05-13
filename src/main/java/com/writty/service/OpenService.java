package com.writty.service;

import com.writty.model.Open;

public interface OpenService {
	
	public Open getOpen(Integer id);
	
	public Open getOpenByOpenId(Long open_id);
	
	public boolean save(Long openId, Long uid);
	
	public boolean delete(Integer id);
		
}
