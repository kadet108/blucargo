package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.CommentDao;
import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CommentService {

	private final CommentDao commentDao;

	@Inject
	public CommentService(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public synchronized void save(Comment comment) {
		this.commentDao.saveOrUpdate(comment);
	}

	public synchronized void remove(Comment comment) {
		this.commentDao.delete(comment);
	}

	public synchronized List<Comment> findAll() {
		return commentDao.findAll();
	}
	
    public synchronized Comment findById(long id){
    	return commentDao.findById(id);
    }

	public List<Comment> findCommentsByOfferId(Long id) {
    	return commentDao.findCommentsByOfferId(id);
	}

	public List<Comment> findCommentsByAuthor(String author) {
    	return commentDao.findCommentsByAuthor(author);
	}
	
	public List<CommentAndOffer> findAllCommentsForUser(String user){
		return commentDao.findAllCommentsForUser(user);
	}

	public List<Comment> findAllCommentsForUserSimple(String user){
		return commentDao.findAllCommentsForUserSimple(user);
	}

	
}
