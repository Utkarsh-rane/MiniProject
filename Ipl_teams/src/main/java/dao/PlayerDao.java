package dao;

import java.sql.Date;
import java.sql.SQLException;

import pojos.Players;


public interface PlayerDao {

	boolean validatePlayer(String firstname,String lastname,String dob,String avg,String wicket ,String teamid)throws SQLException;
  
}
