package com.sjsu.cmpe275.lab3.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.cmpe275.lab3.model.Opponent;
import com.sjsu.cmpe275.lab3.model.Player;
import com.sjsu.cmpe275.lab3.service.OpponentService;
import com.sjsu.cmpe275.lab3.service.PlayerService;

@Controller
public class OpponentController {

	@Autowired
	private OpponentService opponentService;

	@Autowired
	private PlayerService playerService;

	public OpponentService getOpponentService() {
		return opponentService;
	}

	public void setOpponentService(OpponentService opponentService) {
		this.opponentService = opponentService;
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.PUT)
	public ResponseEntity<String> addOpponent(@PathVariable("id1") int playerId1, Model model,
			@PathVariable("id2") int playerId2) {
		System.out.println("Member id1:"+playerId1);
		System.out.println("Member id2:"+playerId2);
		ResponseEntity<String> responseEntity = null;

		try{
			Player player1 = null;
			if(playerService.getPlayer(playerId1) == null) {
				responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				Player player2 = null;
				player2 = playerService.getPlayer(playerId2);
				if(player2 == null) {
					responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
				}else{
					try {
						String response = getOpponentService().addOpponent(playerId1, playerId2);
						if (response == "mapping created")
							responseEntity = new ResponseEntity<String>("Opponent Addition Successful\n\r", HttpStatus.OK);
						else if (response == "mapping exists")
							responseEntity = new ResponseEntity<String>("", HttpStatus.OK);						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteOpponent(@PathVariable("id1") int playerId1, Model model,
			@PathVariable("id2") int playerId2) {
		System.out.println("Member id1:"+playerId1);
		System.out.println("Member id2:"+playerId2);
		ResponseEntity<String> responseEntity = null;
		try{
			Player player1 = null;
			if(playerService.getPlayer(playerId1) == null) {
				responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				Player player2 = null;
				player2 = playerService.getPlayer(playerId2);
				if(player2 == null) {
					responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
				}else{
					try {
						String response = getOpponentService().removeOpponent(playerId1, playerId2);
						if (response == "mapping deleted")
							responseEntity = new ResponseEntity<String>("Opponent Removal Successful\n\r", HttpStatus.OK);
						else if (response == "no mapping exists")
							responseEntity = new ResponseEntity<String>("", HttpStatus.NOT_FOUND);						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
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
