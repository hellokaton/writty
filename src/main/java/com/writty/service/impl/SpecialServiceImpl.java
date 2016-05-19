package com.writty.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.writty.kit.QiniuKit;
import com.writty.kit.Utils;
import com.writty.model.Special;
import com.writty.service.SpecialService;

import blade.kit.DateKit;
import blade.kit.FileKit;
import blade.kit.StringKit;

@Service
public class SpecialServiceImpl implements SpecialService {
	
	@Override
	public Special getSpecial(Long id) {
		return AR.findById(Special.class, id);
	}
	
	@Override
	public List<Special> getSpecialList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Special.class);
		}
		return null;
	}
	
	@Override
	public Page<Map<String, Object>> getPageListMap(String title, Integer page, Integer count) {
		QueryParam up = QueryParam.me();
		if(StringKit.isNotBlank(title)){
			up.like("title", "%"+title+"%");
		}
		if(null == page || page < 1){
			page = 1;
		}
		
		if(null == count || count < 1){
			count = 10;
		}
		up.orderby("id desc").page(page, count);
		Page<Special> pageSpecial = AR.find(up).page(Special.class);
		return this.getPageListMap(pageSpecial);
	}
	
	private Page<Map<String, Object>> getPageListMap(Page<Special> pageSpecial) {
		long totalCount = pageSpecial.getTotalCount();
		int page = pageSpecial.getPage();
		int pageSize = pageSpecial.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Special> specials = pageSpecial.getResults();
		
		List<Map<String, Object>> specialsMaps = this.getListMap(specials);
		
		result.setResults(specialsMaps);
		return result;
	}
	
	private List<Map<String, Object>> getListMap(List<Special> specials){
		List<Map<String, Object>> specialsMaps = new ArrayList<Map<String,Object>>();
		if(null != specials && specials.size() > 0){
			for(Special special : specials){
				Map<String, Object> map = this.getSpecialMap(special, null);
				if(null != map && !map.isEmpty()){
					specialsMaps.add(map);
				}
			}
		}
		return specialsMaps;
	}
	
	@Override
	public boolean save(String title, String slug, String cover, String descrption) {
		try {
			
			File file = new File(cover);
			String cover_path = "";
			if(file.exists()){
				String ext = FileKit.getExtension(file.getName());
				if(StringKit.isBlank(ext)){
					ext = "png";
				}
				
				String key = "special/" + StringKit.getRandomChar(4) + "/" + StringKit.getRandomNumber(4) + "." + ext;
				
				boolean flag = QiniuKit.upload(file, key);
				if(flag){
					cover_path = key;
				}
			}
			
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_special(title, slug, cover, description, created) values(?, ?, ?, ?, ?)",
					title, slug, cover_path, descrption, time).executeUpdate();
			
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

	@Override
	public Map<String, Object> getSpecialMap(Special special, Long id) {
		if(null == special){
			special = this.getSpecial(id);
		}
		if(null != special){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", special.getId());
			map.put("title", special.getTitle());
			map.put("slug", special.getSlug());
			map.put("description", special.getDescription());
			if(StringKit.isNotBlank(special.getCover())){
				map.put("cover", QiniuKit.getUrl(special.getCover()));	
			}
			map.put("post_count", special.getPost_count());
			map.put("follow_count", special.getFollow_count());
			map.put("create_date", DateKit.formatDateByUnixTime(special.getCreated().longValue(), "yyyy-MM-dd"));
			return map;
		}
		return null;
	}

	@Override
	public Integer getSpecialCount(String title, String slug) {
		if(StringKit.isNotBlank(title)){
			return AR.find("select count(1) from t_special where title = ?", title).first(Integer.class);
		}
		if(StringKit.isNotBlank(slug)){
			return AR.find("select count(1) from t_special where slug = ?", slug).first(Integer.class);
		}
		return 0;
	}

	@Override
	public boolean update(Long id, String title, String slug, String cover, String description) {
		
		StringBuffer updateSql = new StringBuffer("update t_special set ");
		List<Object> params = new ArrayList<Object>();
		
		if(StringKit.isNotBlank(title)){
			updateSql.append("title = ?, ");
			params.add(title);
		}
		
		if(StringKit.isNotBlank(slug)){
			updateSql.append("slug = ?, ");
			params.add(slug);
		}
		
		if(null != description){
			updateSql.append("description = ?, ");
			params.add(description);
		}
		
		File file = new File(cover);
		if(file.exists()){
			String ext = FileKit.getExtension(file.getName());
			if(StringKit.isBlank(ext)){
				ext = "png";
			}
			
			String key = "special/" + cover + "/" + StringKit.getRandomChar(4) + "/" + StringKit.getRandomNumber(4) + "." + ext;
			
			boolean flag = QiniuKit.upload(file, key);
			if(flag){
				updateSql.append("cover = ?, ");
				params.add(key);
			}
		}
		String sql = updateSql.substring(0, updateSql.length() - 2) + " where id = ?"; 
		params.add(id);
		
		try {
			AR.update(sql, params.toArray()).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Map<String, Object>> getRandomList() {
		
		Integer max_sid = AR.find("select max(id) from t_special").first(Integer.class) + 1;
		
		int[] randoms = Utils.randomCommon(1000, max_sid, 6);
		
		List<Special> specials = AR.find("select * from t_special where id in(?, ?, ?, ?, ?, ?)", 
				randoms[0], randoms[1], randoms[2], 
				randoms[3], randoms[4], randoms[5] 
				).list(Special.class);
		return this.getListMap(specials);
	}
		
}
