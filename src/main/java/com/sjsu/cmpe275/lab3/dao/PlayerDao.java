package com.sjsu.cmpe275.lab3.dao;

import java.sql.SQLException;

import com.sjsu.cmpe275.lab3.model.Player;

public interface PlayerDao {
	
	public Player addPlayer(Player player) throws SQLException;
	public Player updatePlayer(int playerId, Player player) throws SQLException;
	public Player deletePlayer(int playerId) throws SQLException;
	public Player getPlayer(int playerId) throws SQLException;
	
}
