package com.blucargo;

import org.jivesoftware.openfire.event.SessionEventListener;
import org.jivesoftware.openfire.session.Session;

import com.blucargo.services.OfferAcceptanceService;
import com.google.inject.Inject;

public class BlucargoSessionListener implements SessionEventListener{

	private OfferAcceptanceService offerAcceptanceService;
	
	@Inject
	public BlucargoSessionListener(OfferAcceptanceService offerAcceptanceService){
		this.offerAcceptanceService=offerAcceptanceService;
	}
	
	@Override
	public void anonymousSessionCreated(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anonymousSessionDestroyed(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resourceBound(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(Session session) {
		String nick=session.getAddress().getNode();
		offerAcceptanceService.initiatorLogsOut(nick);
	}

}
