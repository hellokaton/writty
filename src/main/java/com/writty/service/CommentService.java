package com.writty.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.writty.model.Comment;

public interface CommentService {
	
	public Comment getComment(Integer id);
	
	public Comment getComment(QueryParam where);
	
	public List<Comment> getCommentList(QueryParam where);
	
	public Page<Comment> getPageList(QueryParam where);
	
	public boolean save( String pid, Integer cid, Integer uid, String nickName, String siteUrl, String email, String ip, Integer created );
	
	public boolean delete(Integer id);
		
}
