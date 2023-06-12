package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

import com.mysql.cj.xdevapi.PreparableStatement;
import static utils.Dbutils.*;
import pojos.Players;

public class PlayerDaoImpl implements PlayerDao{
      private Connection cn;
      private PreparedStatement pst1;
      
      public PlayerDaoImpl() throws SQLException{
    	  cn=openConnection();
    	  pst1=cn.prepareStatement("insert into teams values(default,?,?,?,?,?,?)");
      System.out.println("user dao created");
      }

      @Override
  	public boolean validatePlayer(String firstname, String lastname, String dob, String avg, String wicket,
  			String teamid) throws SQLException {
    	  Date db=Date.valueOf(dob);
  		double avg1=Double.parseDouble(avg);
  		int wicket1=Integer.parseInt(wicket);
  		int teamid1=Integer.parseInt(teamid);
  		if(Period.between(LocalDate.parse(dob), LocalDate.now()).getYears()<30) {
  			pst1.setString(1, firstname);
  			pst1.setString(2, lastname);
  			pst1.setDate(3, db);
  			pst1.setDouble(4, avg1);
  			pst1.setInt(5, wicket1);
  			pst1.setInt(6, teamid1);
  			int rst1=pst1.executeUpdate();
  			if(rst1>0) {
  				return true;
  			}
  		}
  		
  		return false;

  	}
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		
		closeConnection();
		System.out.println("user dao cleaned up!");
	}


	
   
}
