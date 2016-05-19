package com.writty.service;

import java.util.List;
import java.util.Map;

import com.writty.model.Comment;

public interface CommentService {
	
	Comment getComment(Integer id);
	
	List<Map<String, Object>> getCommentListMap(String pid);
	
	boolean save(String pid, Long cid, Long uid, Long to_uid, Long post_uid, String content, String ip);
	
}
