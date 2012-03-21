package com.blucargo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import com.blucargo.interfaces.ICommentDao;
import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CommentDao extends BaseDao<Comment,Long> implements ICommentDao {

	protected CommentDao() {
		super(Comment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findCommentsByOfferId(Long id) {
    	String queryString = "SELECT c from Comment c where c.cargoOfferId=:offerId";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("offerId", id);
		return (List<Comment>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findCommentsByAuthor(String author) {
    	String queryString = "SELECT c from Comment c where c.author=:author";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("author", author);
		return (List<Comment>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	// Using View
	public List<CommentAndOffer> findAllCommentsForUser(String user) {
    	String queryString =  "SELECT c FROM CommentAndOffer c where c.owner=:user";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("user", user);
		List<CommentAndOffer> o = (List<CommentAndOffer>) query.getResultList();
		return o;
	}

	@SuppressWarnings("unchecked")
	// Using View
	public List<Comment> findAllCommentsForUserSimple(String owner) {
    	String queryString =  "SELECT DISTINCT t3 from Comment t3 where t3.cargoOfferId in (select offer.id from CargoOffer offer where offer.owner=:owner)";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("owner", owner);
		List<Comment> o = (List<Comment>) query.getResultList();
		
		List<Comment> ordinaryList = new ArrayList<Comment>(o);
		
//		Collections.copy(ordinaryList, o);
		
		return ordinaryList;
	}
	
	

}
