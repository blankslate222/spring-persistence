package com.sjsu.cmpe275.lab3.model;

import java.io.Serializable;

public class Opponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int player1;
	private int player2;
	
	public int getPlayer1() {
		return player1;
	}
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}
	public int getPlayer2() {
		return player2;
	}
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	
	
}