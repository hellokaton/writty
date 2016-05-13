package com.writty.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;
import com.writty.model.Open;
import com.writty.service.OpenService;

import blade.kit.DateKit;

@Service
public class OpenServiceImpl implements OpenService {
	
	@Override
	public Open getOpen(Integer id) {
		return AR.findById(Open.class, id);
	}
	
	@Override
	public Open getOpenByOpenId(Long open_id) {
		if(null != open_id){
			return AR.find(QueryParam.me().eq("open_id", open_id)).first(Open.class);
		}
		return null;
	}
	
	@Override
	public boolean save(Long openId, Long uid) {
		try {
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_open(open_id, uid, created) values(?, ?, ?)", openId, uid, time).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
