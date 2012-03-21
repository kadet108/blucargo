package com.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.CommentDao;
import com.blucargo.model.Comment;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class CommentDaoDbTest extends TransactionRollbackTestBase{

	@Inject
    CommentDao commentDao;

	
	@Before
	public void setUp(){
	}
	

	@Test
	public void testSaveComment(){
		
		Comment c=new Comment();
		c.setAuthor("a");
		commentDao.saveOrUpdate(c);
		
		List<Comment> comments=commentDao.findAll();
		
		assertEquals(1,comments.size());
		assertEquals(c.getId(),comments.get(0).getId());
		
	}
	
	@Test
	public void testCommentHasDate(){
		
		Comment c=new Comment();
		c.setAuthor("a");
		commentDao.saveOrUpdate(c);
		
		List<Comment> comments=commentDao.findAll();
		
		assertEquals(1,comments.size());
		assertNotNull(comments.get(0).getDate());
		
	}

}
