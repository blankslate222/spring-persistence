package com.sjsu.cmpe275.lab3.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe275.lab3.daoimpl.PlayerDaoImpl;
import com.sjsu.cmpe275.lab3.model.Player;

@Service
public class PlayerService {

	@Autowired
	private PlayerDaoImpl playerDaoImpl;

	public PlayerDaoImpl getPlayerDaoImpl() {
		return playerDaoImpl;
	}

	public void setPlayerDaoImpl(PlayerDaoImpl playerDaoImpl) {
		this.playerDaoImpl = playerDaoImpl;
	}
	
	public Player createPlayer(Player player) throws SQLException {
		return getPlayerDaoImpl().addPlayer(player);
	}
	
	public Player getPlayer(int id) throws SQLException {
		return getPlayerDaoImpl().getPlayer(id);
	}

}
