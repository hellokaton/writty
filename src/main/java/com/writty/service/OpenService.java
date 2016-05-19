package com.writty.service;

import com.writty.model.Open;

public interface OpenService {
	
	Open getOpen(Integer id);
	
	Open getOpenByOpenId(Long open_id);
	
	boolean save(Long openId, Long uid);
	
	boolean delete(Integer id);
		
}
