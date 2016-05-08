package com.writty.service.impl;

import java.util.List;

import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.blade.ioc.annotation.Service;
import com.writty.model.Comment;
import com.writty.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Override
	public Comment getComment(Integer id) {
		return AR.findById(Comment.class, id);
	}
	
	@Override
	public Comment getComment(QueryParam where) {
		if(null != where){
			return AR.find(where).first(Comment.class);
		}
		return null;
	}
	
	@Override
	public List<Comment> getCommentList(QueryParam where) {
		if(null != where){
			return AR.find(where).list(Comment.class);
		}
		return null;
	}
	
	@Override
	public Page<Comment> getPageList(QueryParam where) {
		Page<Comment> pageComment = AR.find(where).page(Comment.class);
		return pageComment;
	}
	
	@Override
	public boolean save( String pid, Integer cid, Integer uid, String nickName, String siteUrl, String email, String ip, Integer created ) {
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
