package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;

	public UserDaoImpl() throws SQLException {
		// open conn
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2=cn.prepareStatement("update users set status=1 where id=?");
		pst3 =cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		System.out.println("user dao created!");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);		
		pst1.setString(2, password);
		try(ResultSet rst=pst1.executeQuery()) {
			if(rst.next()) //=> success
				/*
				 * int id, String firstName, String lastName, String email, String password, Date dob,
			boolean votingStatus, String role
				 */
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, 
						rst.getDate(6), rst.getBoolean(7), rst.getString(8));
		}//rst.close()
		return null;
	}
	

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		// set IN param : voter id
		pst2.setInt(1, voterId);
		//exec method : exec update
		int updateCount=pst2.executeUpdate();
		if(updateCount==1)
			return "Updated voting status";
		return "Updation failed!!!!!";
	}

	@Override
	public boolean registeruser(String firstName, String lastName, String email, String password, String dob)
			throws SQLException {
		Date ld=Date.valueOf(dob);
		boolean status=false;
		String role="voter";
		
		if(Period.between(LocalDate.parse(dob), LocalDate.now()).getYears()>21)
		{
			pst3.setString(1, firstName);		
			pst3.setString(2, lastName);
			pst3.setString(3, email);		
			pst3.setString(4, password);
			pst3.setDate(5, ld);
			pst3.setBoolean(6, status);;
			pst3.setString(7, role);
			int rst1=pst3.executeUpdate();
			if(rst1>0)
			{
				return true;
			}

		}
		return false;
	}
	
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		closeConnection();
		System.out.println("user dao cleaned up!");
	}

}
