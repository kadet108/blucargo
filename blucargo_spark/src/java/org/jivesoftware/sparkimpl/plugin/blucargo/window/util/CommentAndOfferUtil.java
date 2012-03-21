package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;
import java.util.List;

import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;

import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;

public class CommentAndOfferUtil {
	
	

		public static OfferCommentsAndContact getOfferCommentsAndContact(String nickname)
		{
			List<CommentAndOffer> ls = CargoDataManager.getCommentDao().findAllCommentsForUser(nickname);
			//OfferCommentsAndContact offCmtCon = new OfferCommentsAndContact(ls, nickname);
			return null;
		}
		
		public static class OfferCommentsAndContact
		{
			private Comment commentAndContact;
			private String nickname;
			
			public OfferCommentsAndContact(List<Comment> ls, String nickname)
			{
				this.nickname = nickname;
				if( ls != null && ls.size() > 0 )
		        {
		        	for( Comment cmt : ls )
		        	{
		        		cmt.getAuthor();
		        		if( cmt.getAuthor().equals( nickname ) )
		        		{
		        			commentAndContact = cmt;
		        		}
		        	}
		        }
			}
			
			public String getOwnerNickname() {return nickname; }
						
			public Comment getCommentAndContact() {return commentAndContact; }
			
			public boolean areCommentsAvailable() { return isCommentAndContactAvailable(); }
			
			public boolean isCommentAndContactAvailable() { return commentAndContact != null; }
			
			public void setCommentAndContact(Comment commentAndCargoOffer) {
				commentAndContact = commentAndCargoOffer;
			}
		}

}
