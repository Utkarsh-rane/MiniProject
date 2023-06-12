package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Teams;

public interface TeamDao {
  List<Teams> getteamDetails() throws SQLException;
}
