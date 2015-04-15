package com.sjsu.cmpe275.lab3.dao;

import java.sql.SQLException;

public interface OpponentDao {

	public String addOpponent(int playerId1, int playerId2) throws SQLException;
	public String removeOpponent(int playerId1, int playerId2) throws SQLException;
	
}
