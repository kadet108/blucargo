package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.util.List;

import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;

import com.blucargo.model.Comment;

public class OfferCommentsUtils {

	public static OfferComments getOfferComments(Long offerId, String nickname)
	{
		List<Comment> ls = CargoDataManager.getCommentDao().findCommentsByOfferId(offerId);
		OfferComments offCmt = new OfferComments(ls, nickname);
		return offCmt;
	}
	
	public static class OfferComments
	{
		private Comment ownerComment;
		private Comment otherComment;
		private String nickname;
		
		public OfferComments(List<Comment> ls, String nickname)
		{
			this.nickname = nickname;
			if( ls != null && ls.size() > 0 )
	        {
	        	for( Comment cmt : ls )
	        	{
	        		cmt.getAuthor();
	        		if( cmt.getAuthor().equals( nickname ) )
	        		{
	        			ownerComment = cmt;
	        		}
	        		else
	        		{
	        			otherComment = cmt;
	        		}
	        	}
	        }
		}
		
		public String getOwnerNickname() {return nickname; }
		
		public Comment getOwnerComment() {return ownerComment; }
		
		public Comment getOtherComment() {return otherComment; }
		
		public boolean areCommentsAvailable() { return isOwnerCommentAvailable() || isOtherCommentAvailable(); }
		
		public boolean isOwnerCommentAvailable() { return ownerComment != null; }
		
		public boolean isOtherCommentAvailable() { return otherComment != null; }

		public void setOwnerComment(Comment comment) {
			ownerComment = comment;
		}
	}
}
