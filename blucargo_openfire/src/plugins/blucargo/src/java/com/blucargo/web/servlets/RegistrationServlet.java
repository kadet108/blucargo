package com.blucargo.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.user.UserAlreadyExistsException;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.smackx.provider.VCardProvider;
import org.jivesoftware.util.Log;

import com.blucargo.BlucargoPlugin;
import com.blucargo.model.RegistrationData;
import com.blucargo.utils.validation.InputFieldVerifier;

public class RegistrationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init()
	{	
		
	}
	
	public boolean sendMail(
            String hostName, int port, String smtpUser, String smtpPassword,
            String[] toArr, String from, String subject, String body)
    {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostName);
        email.setSmtpPort(port);

        try
        {
            email.setFrom(from, from);
            for (String to : toArr)
            {
                email.addTo(to);
            }
            email.setAuthentication(smtpUser, smtpPassword);
            email.setSubject(subject);
            email.setMsg(body);
            email.send();
        }
        catch (EmailException e)
        {
            //log.error(e.getMessage());
            return false;
        }

        return true;
    } 
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/plain");
		String login = request.getParameter("login");
		String pswd = request.getParameter("password");
		String email = request.getParameter("email");
		String vcard = request.getParameter("vCard");
		
		if( !inputFieldVerifier(login, pswd, email, vcard) )
			return;
		
		if( !checkLogin(login) )
		{
			try {
				response.getWriter().write("SUCH_USER_EXIST");
				response.getWriter().close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		inputFieldVerifier(login, pswd, email, vcard);
		
		UUID regUuid = UUID.randomUUID();
		RegistrationData regData = new RegistrationData();
		regData.setPassword(pswd);
		regData.setRegId(regUuid.toString());
		regData.setUserName(login);
		regData.setvCard(vcard);
		
		String responseData = "REG_OK";
		if( BlucargoPlugin.get().getRegistrationService() != null )
		{
			try{
				BlucargoPlugin.get().getRegistrationService().save(regData);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			String[] toUser  = { email };
			String smtpHost = "sv1.vipserv.org";
			String smtpAccount = "test@lunisoft.pl";
			String smtpPswd = "testPassword";
			sendMail(smtpHost, 25, smtpAccount, smtpPswd, toUser, smtpAccount, "Openfire",
					"http://localhost:9090/cargo/registration.do?regId=" + regUuid.toString());

		}
		else
		{
			responseData = "REG_FAIL";
		}
		
		try {
			response.setContentType("text/plain");
			response.getWriter().write(responseData);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean inputFieldVerifier(String login, String pswd, String email,
			String vcard) {

		VCard vCard = new VCard();
		vCard =  makeVCardFromString(vcard);
		String address = vCard.getAddressFieldWork("LOCALITY");
		String city = vCard.getAddressFieldWork("REGION");
		String country = vCard.getAddressFieldWork("CTRY");
		String cell = vCard.getPhoneWork("CELL");
		String nip = vCard.getField("NIP");
		String post = vCard.getField("POST");
		
		if(
				( InputFieldVerifier.get().verifyUsername(login) > 0) &&
				( InputFieldVerifier.get().isPasswordValid(pswd, pswd) > 0) &&
				( InputFieldVerifier.get().verifyEmail(email) > 0) &&
				( InputFieldVerifier.get().verifyName(vCard.getFirstName()) > 0) &&
				( InputFieldVerifier.get().verifySurname(vCard.getLastName() )  > 0) &&
				( InputFieldVerifier.get().verifyCompanyName(vCard.getOrganization() )  > 0) &&
				( InputFieldVerifier.get().verifyAdress(address) > 0) &&
				( InputFieldVerifier.get().verifyCity( city ) > 0) &&
				( InputFieldVerifier.get().verifyCountry( post, country ) > 0) &&
				( InputFieldVerifier.get().verifyPostCode(post, country) > 0) &&
				( InputFieldVerifier.get().verifyCell( cell) > 0) &&
				( InputFieldVerifier.get().verifyNip( nip ) > 0)
			)
		return false;
		else
			return true;
	}	

	private boolean checkLogin(String login) 
	{
		boolean res = true;
		try {
			Connection conn = DbConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select exists (select username from ofUser where username=?)");
            pstmt.setString(1, login);
            if( pstmt.execute() )
            {
            	ResultSet rs = pstmt.getResultSet();
            	if( rs.next() && rs.getBoolean(1) )
            	{
            		res = false;
            	}
            }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if( res ){
			res = !BlucargoPlugin.get().getRegistrationService().checkLoginInTable(login);
		}
		return res;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		String regId= null;
		regId = request.getParameter("regId");
		String view = "http://www.blucargo.pl/" + (copyDataFromRegistrationToOfUserByRegId( regId ) ? 
			"registerok.php" : "registerfailed.php");

		try {
			response.sendRedirect(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean copyDataFromRegistrationToOfUserByRegId(String regId){
		boolean result = false;
		
		
		int isUpdated = 0;
		boolean abortTransaction = false;
		PreparedStatement pstmt = null;
		RegistrationData registrationData = BlucargoPlugin.get().getRegistrationService().getUserByRegistrationNumber(regId);
		
		VCard vCard = new VCard();
		String vcardStr = registrationData.getvCard();
		vCard = makeVCardFromString(vcardStr);

		Connection conn = null;
		try {
			conn = DbConnectionManager.getConnection();
			
			try {
				XMPPServer.getInstance().getUserManager().createUser(registrationData.getUserName(), registrationData.getPassword(), vCard.getFirstName(), vCard.getEmailWork());
			} catch (UserAlreadyExistsException e) {
				e.printStackTrace();
			}
		
			 pstmt = conn.prepareStatement(
					"INSERT INTO ofUser( creationDate, modificationDate) " +
					"VALUES (?, ?)");
			 
		     Date date = new Date((new java.util.Date()).getTime());
		     pstmt.setDate(6, date);
		     pstmt.setDate(7, new Date(date.getTime()));
		     isUpdated = pstmt.executeUpdate();
	           if(isUpdated != 0)
	            {
	            	BlucargoPlugin.get().getRegistrationService().deleteAllByRegId(regId);
	            }
		} catch (SQLException e) {
			Log.error(e);
            abortTransaction = true;
			e.printStackTrace();
		}finally {
            try {if ( pstmt != null) {pstmt.close();}}
            catch (Exception e) {Log.error(e);}
            DbConnectionManager.closeTransactionConnection(conn, abortTransaction);
        }
		
		if(isUpdated != 0){
			try {
				abortTransaction = false;
				conn = DbConnectionManager.getConnection();
				pstmt = conn.prepareStatement(
						"INSERT INTO ofVCard(username, vcard) " +
						"VALUES (?, ?)");
			     pstmt.setString(1, registrationData.getUserName());
			     pstmt.setString(2, registrationData.getvCard());
			     if(pstmt.executeUpdate() != 0){
			    	 result = true;
			     }
			} catch (Exception e1) {
				Log.error(e1);
	            abortTransaction = true;
				e1.printStackTrace();
			}finally {
	            try {if ( pstmt != null) {pstmt.close();}}
	            catch (Exception e) {Log.error(e);}
	            DbConnectionManager.closeTransactionConnection(conn, abortTransaction);
	        }
		}
		
		return result;
	}
	
	public VCard makeVCardFromString(String vcardStr){
		VCard vCard = new VCard();
		try {
			vCard = VCardProvider.createVCardFromXML(vcardStr);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return vCard;
	}
	
	
}