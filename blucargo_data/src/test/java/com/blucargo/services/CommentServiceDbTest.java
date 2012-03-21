package com.blucargo.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;
import com.blucargo.model.EndedOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class CommentServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    CommentService commentService;
	@Inject
	CargoOfferService cargoOfferService;
	@Inject
	EndedOfferService endedOfferService;
	
	@Before
	public void setUp(){
	}

	@Test
	public void testFindCommentsByOfferId(){
		CargoOffer o=new CargoOffer();
		cargoOfferService.save(o);
		
		Comment c1=new Comment();
		c1.setCargoOfferId(o.getId());
		c1.setAuthor("a");
		
		Comment c2=new Comment();
		c2.setCargoOfferId(o.getId());
		c2.setAuthor("a");
		
		Comment c3=new Comment();
		c3.setCargoOfferId(o.getId());
		c3.setAuthor("a");

		Comment c4=new Comment();
		c4.setCargoOfferId(o.getId());
		c4.setAuthor("a");
		
		commentService.save(c1);
		commentService.save(c2);
		commentService.save(c3);
		commentService.save(c4);

		List<Comment> comments=commentService.findCommentsByOfferId(o.getId());
		assertEquals(4,comments.size());

		List<Comment> comments2=commentService.findCommentsByOfferId(3L);
		assertEquals(0,comments2.size());

	}
	
	@Test
	public void testFindCommentsByAuthor(){
		CargoOffer o1=new CargoOffer();
		cargoOfferService.save(o1);
		CargoOffer o2=new CargoOffer();
		cargoOfferService.save(o1);
		CargoOffer o3=new CargoOffer();
		cargoOfferService.save(o1);
		CargoOffer o4=new CargoOffer();
		cargoOfferService.save(o1);

		Comment c1=new Comment();
		c1.setAuthor("a");
		c1.setCargoOfferId(o1.getId());
		Comment c2=new Comment();
		c2.setAuthor("a");
		c2.setCargoOfferId(o2.getId());
		Comment c3=new Comment();
		c3.setAuthor("a");
		c3.setCargoOfferId(o3.getId());
		Comment c4=new Comment();
		c4.setAuthor("a");
		c4.setCargoOfferId(o4.getId());
		
		//Dummy comments
		Comment c5=new Comment();
		c5.setAuthor("b");
		c5.setCargoOfferId(o4.getId());
		Comment c6=new Comment();
		c6.setAuthor("b");
		c6.setCargoOfferId(o4.getId());
		
		commentService.save(c1);
		commentService.save(c2);
		commentService.save(c3);
		commentService.save(c4);
		
		List<Comment> comments=commentService.findCommentsByAuthor("a");
		assertEquals(4,comments.size());
		assertEquals(o1.getId(),comments.get(0).getCargoOfferId());
		assertEquals(o2.getId(),comments.get(1).getCargoOfferId());
		assertEquals(o3.getId(),comments.get(2).getCargoOfferId());
		assertEquals(o4.getId(),comments.get(3).getCargoOfferId());
		
	}

	@Test
	public void testFindCommentsByUsername(){
		CargoOffer o1=new CargoOffer();
		o1.setOwner("b");
		CargoOffer o2=new CargoOffer();
		o2.setOwner("b");
		CargoOffer o3=new CargoOffer();
		o3.setOwner("b");
		CargoOffer o4=new CargoOffer();
		o4.setOwner("b");
		cargoOfferService.save(o1);
		cargoOfferService.save(o2);
		cargoOfferService.save(o3);
		cargoOfferService.save(o4);
		cargoOfferService.findAll();
		
		//Ended Offers.
		EndedOffer eo1 = new EndedOffer();
		eo1.setOfferId(o1.getId());
		eo1.setUserName("a");
		EndedOffer eo2 = new EndedOffer();
		eo2.setOfferId(o2.getId());
		eo2.setUserName("a");
		EndedOffer eo3 = new EndedOffer();
		eo3.setOfferId(o3.getId());
		eo3.setUserName("a");
		EndedOffer eo4 = new EndedOffer();
		eo4.setOfferId(o4.getId());
		eo4.setUserName("a");

		endedOfferService.save(eo1);
		endedOfferService.save(eo2);
		endedOfferService.save(eo3);
		endedOfferService.save(eo4);
		endedOfferService.findAll();
		
		Comment c1=new Comment();
		c1.setAuthor("a");
		c1.setCargoOfferId(o1.getId());
		Comment c2=new Comment();
		c2.setAuthor("a");
		c2.setCargoOfferId(o2.getId());
		Comment c3=new Comment();
		c3.setAuthor("a");
		c3.setCargoOfferId(o3.getId());
		Comment c4=new Comment();
		c4.setAuthor("a");
		c4.setCargoOfferId(o4.getId());
		
		//Dummy comments
		Comment c5=new Comment();
		c5.setAuthor("b");
		c5.setCargoOfferId(o4.getId());
		Comment c6=new Comment();
		c6.setAuthor("b");
		c6.setCargoOfferId(o4.getId());
		
		commentService.save(c1);
		commentService.save(c2);
		commentService.save(c3);
		commentService.save(c4);
		
		List<Comment> comments=commentService.findAllCommentsForUserSimple("b");
		assertEquals(4,comments.size());
		assertEquals(o1.getId(),comments.get(0).getCargoOfferId());
		assertEquals(o2.getId(),comments.get(1).getCargoOfferId());
		assertEquals(o3.getId(),comments.get(2).getCargoOfferId());
		assertEquals(o4.getId(),comments.get(3).getCargoOfferId());
		
	}

	
}
