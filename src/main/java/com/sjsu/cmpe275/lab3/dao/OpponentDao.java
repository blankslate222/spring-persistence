package com.sjsu.cmpe275.lab3.dao;

public interface OpponentDao {

	public void addOpponent(int playerId1, int playerId2);
	public void removeOpponent(int playerId1, int playerId2);
	
}
