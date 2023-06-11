package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface AdminDao {
	List<Candidate> toptwo() throws SQLException;
	List<Candidate> votesAnalysis() throws SQLException;
}
