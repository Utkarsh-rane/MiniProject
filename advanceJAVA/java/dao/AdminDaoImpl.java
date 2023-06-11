package dao;

import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;

public class AdminDaoImpl implements AdminDao{
private Connection cn;
private PreparedStatement pst1,pst2;
	
   public AdminDaoImpl() throws SQLException {
	   cn = openConnection();
		pst1 = cn.prepareStatement("select * from candidates order by votes desc limit 2");
		pst2=cn.prepareStatement("select party ,sum(votes) from candidates group by party");
   }

@Override
    public List<Candidate> toptwo() throws SQLException {
	List<Candidate> topTwoCandidate=new ArrayList<>();
	try(ResultSet rst1=pst1.executeQuery()){
		while(rst1.next())
		{
			topTwoCandidate.add(new Candidate(rst1.getInt(1), rst1.getString(2), 
					rst1.getString(3), rst1.getInt(4)));
		}
		return topTwoCandidate;
	}
}

@Override
public List<Candidate> votesAnalysis() throws SQLException {
	List<Candidate> partylist=new ArrayList<>();
	try(ResultSet rst2=pst2.executeQuery()){
		while(rst2.next())
		{
			partylist.add(new Candidate(rst2.getString(1), rst2.getInt(2)));
		}
		return partylist;
	}
}
   
}
