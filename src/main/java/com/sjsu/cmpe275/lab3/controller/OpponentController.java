package com.sjsu.cmpe275.lab3.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.cmpe275.lab3.model.Opponent;
import com.sjsu.cmpe275.lab3.service.OpponentService;

@Controller
public class OpponentController {

	@Autowired
	private OpponentService opponentService;

	public OpponentService getOpponentService() {
		return opponentService;
	}

	public void setOpponentService(OpponentService opponentService) {
		this.opponentService = opponentService;
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.PUT)
	public String addOpponent(@PathVariable("id1") int playerId1, Model model,
			@PathVariable("id2") int playerId2) {
		System.out.println("Member id1:"+playerId1);
		System.out.println("Member id2:"+playerId2);
		Opponent response = null;
		try {
			response = getOpponentService().addOpponent(playerId1, playerId2);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "jsonview";
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.DELETE)
	public String deleteOpponent(@PathVariable("id1") int playerId1, Model model,
			@PathVariable("id2") int playerId2) {
		System.out.println("Member id1:"+playerId1);
		System.out.println("Member id2:"+playerId2);
		Opponent response = null;
		try {
			response = getOpponentService().removeOpponent(playerId1, playerId2);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "jsonview";
	}

	/*
	@RequestMapping(value="/opponent", method = RequestMethod.GET)
	@ResponseBody
	public Opponent addOpponent(@ModelAttribute("opponent") Opponent opponent, BindingResult result) {
		
		Opponent response = null;
		try {
			response = getOpponentService().addOpponent(opponent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}*/

}
