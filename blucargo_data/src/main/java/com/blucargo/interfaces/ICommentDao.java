package com.blucargo.interfaces;

import java.util.List;

import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;

public interface ICommentDao {
	public abstract List<Comment> findCommentsByOfferId(Long id);
	public abstract List<Comment> findCommentsByAuthor(String author);
	public abstract List<CommentAndOffer> findAllCommentsForUser(String user);
	public abstract List<Comment> findAllCommentsForUserSimple(String user);
}