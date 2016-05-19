package com.writty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.writty.model.Options;
import com.writty.service.OptionsService;

import blade.kit.CollectionKit;

@Service
public class OptionsServiceImpl implements OptionsService {
	
	@Override
	public Options getOptions(String okey) {
		return AR.findById(Options.class, okey);
	}
	
	@Override
	public boolean delete(String okey) {
		if(null != okey){
			AR.update("delete from t_options where okey = ").executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Options> settings = AR.find("select * from t_options").cache(false).list(Options.class);
		if(CollectionKit.isNotEmpty(settings)){
			for(Options setting : settings){
				map.put(setting.getOkey(), setting.getOvalue());
			}
		}
		return map;
	}
	
	@Override
	public boolean update(String site_title, String site_keywords, String site_description) {
		
		return false;
	}
		
}
