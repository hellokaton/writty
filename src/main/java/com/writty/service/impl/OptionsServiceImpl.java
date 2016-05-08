package com.writty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
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
	public Options getOptions(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Options.class);
		}
		return null;
	}
	
	@Override
	public List<Options> getOptionsList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Options.class);
		}
		return null;
	}
	
	@Override
	public Page<Options> getPageList(QueryParam where) {
		Page<Options> pageOptions = AR.find(where).page(Options.class);
		return pageOptions;
	}
	
	@Override
	public boolean save( String ovalue ) {
		return false;
	}
	
	@Override
	public boolean delete(String okey) {
		if(null != okey){
			AR.update("delete from ").executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Options> settings = AR.find("select * from t_options").list(Options.class);
		if(CollectionKit.isNotEmpty(settings)){
			for(Options setting : settings){
				map.put(setting.getOkey(), setting.getOvalue());
			}
		}
		return map;
	}
		
}
