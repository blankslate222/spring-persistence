package com.sjsu.cmpe275.lab3.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe275.lab3.daoimpl.OpponentDaoImpl;
import com.sjsu.cmpe275.lab3.model.Opponent;

@Service
public class OpponentService {
	
	@Autowired
	private OpponentDaoImpl opponentDao;
	
	public OpponentDaoImpl getOpponentDao() {
		return opponentDao;
	}

	public void setOpponentDao(OpponentDaoImpl opponentDao) {
		this.opponentDao = opponentDao;
	}

	public String addOpponent(int playerId1, int playerId2) throws SQLException {
		
		String result = getOpponentDao().addOpponent(playerId1, playerId2);
		
		return result;
	}

	public String removeOpponent(int playerId1, int playerId2) throws SQLException {
		
		String result = getOpponentDao().removeOpponent(playerId1, playerId2);
		
		return result;
	}
}
