package com.sjsu.cmpe275.lab3.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe275.lab3.daoimpl.OpponentDaoImpl;
import com.sjsu.cmpe275.lab3.daoimpl.PlayerDaoImpl;
import com.sjsu.cmpe275.lab3.daoimpl.SponsorDaoImpl;
import com.sjsu.cmpe275.lab3.model.Player;
import com.sjsu.cmpe275.lab3.model.Sponsor;

@Service
public class PlayerService {

	@Autowired
	private PlayerDaoImpl playerDaoImpl;
	@Autowired
	private SponsorDaoImpl sponsorDaoImpl;
	@Autowired
	private OpponentDaoImpl opponentDaoImpl;

	public PlayerDaoImpl getPlayerDaoImpl() {
		return playerDaoImpl;
	}

	public void setPlayerDaoImpl(PlayerDaoImpl playerDaoImpl) {
		this.playerDaoImpl = playerDaoImpl;
	}
	
	public SponsorDaoImpl getSponsorDaoImpl() {
		return sponsorDaoImpl;
	}

	public void setSponsorDaoImpl(SponsorDaoImpl sponsorDaoImpl) {
		this.sponsorDaoImpl = sponsorDaoImpl;
	}
	
	public OpponentDaoImpl getOpponentDaoImpl() {
		return opponentDaoImpl;
	}

	public void setOpponentDaoImpl(OpponentDaoImpl opponentDaoImpl) {
		this.opponentDaoImpl = opponentDaoImpl;
	}

	public Player createPlayer(Player player) throws SQLException {
		return getPlayerDaoImpl().addPlayer(player);
	}
	
	public Player getPlayer(int id) throws SQLException {
		Player player = getPlayerDaoImpl().getPlayer(id);
		Sponsor sponsor = player.getSponsor();
		sponsor = getSponsorDaoImpl().getSponsor(sponsor.getId());
		player.setSponsor(sponsor);
		List<String> opponents = getOpponentDaoImpl().getOpponents(id);
		player.setOpponents(opponents);
		return player;
	}
	
	public Player updatePlayer(int id, Player player) throws SQLException{
		return getPlayerDaoImpl().updatePlayer(id, player);
	}

}
