package com.blucargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.openfire.user.DefaultUserProvider;
import org.jivesoftware.openfire.user.UserNotFoundException;

public class BlucargoUserProvider extends DefaultUserProvider{
	
    private static final String GET_EMPLOYEES_OF =
    	"select * from ofuserprop where name='employeeOf' AND propValue=?";

    public List<String> getEmployeesOf(String username) throws UserNotFoundException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DbConnectionManager.getConnection();
            pstmt = con.prepareStatement(GET_EMPLOYEES_OF);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            List<String> userNames=new ArrayList<String>();

            while(rs.next()){
            	userNames.add(rs.getString("username"));
            }

            return userNames;
        }
        catch (Exception e) {
            throw new UserNotFoundException(e);
        }
        finally {
            DbConnectionManager.closeConnection(rs, pstmt, con);
        }
    }
	

}
