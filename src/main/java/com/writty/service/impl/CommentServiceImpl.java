package com.writty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;
import com.writty.kit.Utils;
import com.writty.model.Comment;
import com.writty.model.User;
import com.writty.service.CommentService;
import com.writty.service.UserService;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private UserService userService;
	
	@Override
	public Comment getComment(Integer id) {
		return AR.findById(Comment.class, id);
	}
	
	@Override
	public boolean save(String pid, Long cid, Long uid, Long to_uid, Long post_uid, String content, String ip) {
		try {
			AR.update("insert into t_comment(pid, cid, uid, to_uid, content, ip, created) values(?, ?, ?, ?, ?, ?)",
					pid, cid, uid, to_uid, content, ip, DateKit.getCurrentUnixTime()).executeUpdate();
			
			// 通知文章发布人
			// 通知回复的人
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Map<String, Object>> getCommentListMap(String pid) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Comment> comments = this.getComments(pid);
		if(null != comments){
			for(Comment comment : comments){
				Map<String, Object> map = this.getCommentMap(comment);
				if(null != map && !map.isEmpty()){
					result.add(map);
				}
			}
		}
		return result;
	}
	
	private Map<String, Object> getCommentMap(Comment comment) {
		if(null != comment){
			User user = userService.getUser(comment.getUid());
			if(null == user){
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", comment.getId());
			map.put("avatar", user.getAvatar());
			map.put("user_name", user.getUser_name());
			map.put("publish_uid", comment.getUid());
			map.put("publish_user", user.getNick_name());
			
			Long to_uid = comment.getTo_uid();
			if(null != to_uid){
				User toUser = userService.getUser(to_uid);
				if(null != toUser){
					map.put("to_user_name", toUser.getUser_name());
					map.put("to_user", toUser.getNick_name());
					map.put("to_uid", to_uid);
				}
			}
			map.put("content", Utils.markdown2html(comment.getContent()));
			return map;
		}
		return null;
	}

	private List<Comment> getComments(String pid){
		if(StringKit.isBlank(pid)){
			return null;
		}
		QueryParam q = QueryParam.me();
		q.eq("pid", pid).orderby("created desc");
		return AR.find(q).list(Comment.class);
	}
	
}
