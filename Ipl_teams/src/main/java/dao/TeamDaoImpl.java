package dao;

import static utils.Dbutils.closeConnection;
import static utils.Dbutils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Teams;

public class TeamDaoImpl implements TeamDao {
	private Connection cn;
	private PreparedStatement pst1;

	public TeamDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select team_id,abbrevation from teams ");
	}

	@Override
	public List<Teams> getteamDetails() throws SQLException {
		List<Teams> teams = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				teams.add(new Teams(rst.getInt(1), rst.getString(2)));
			}
		}

		return teams;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();

		closeConnection();
		System.out.println("user dao cleaned up!");
	}

}
