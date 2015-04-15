package com.sjsu.cmpe275.lab3.dao;

import java.sql.SQLException;

public interface OpponentDao {

	public void addOpponent(int playerId1, int playerId2) throws SQLException;
	public void removeOpponent(int playerId1, int playerId2) throws SQLException;
	
}
