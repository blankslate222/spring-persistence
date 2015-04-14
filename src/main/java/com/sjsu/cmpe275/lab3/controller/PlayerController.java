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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.cmpe275.lab3.model.Player;
import com.sjsu.cmpe275.lab3.model.Sponsor;
import com.sjsu.cmpe275.lab3.service.PlayerService;

@Controller
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/player/create", method = RequestMethod.GET)
	public String getPlayerForm(Model model) {
		Player newPlayer = new Player();
		newPlayer.setSponsor(new Sponsor());
		model.addAttribute("player", newPlayer);
		return "createPlayer";
	}

	@RequestMapping(value = "/player/create", method = RequestMethod.POST, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> createPlayer(
			@ModelAttribute("player") Player player, BindingResult result) {
		ResponseEntity<Player> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<Player>(
					playerService.createPlayer(player), HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> getPlayer(@PathVariable("id") String id, Model model) {
		ResponseEntity<Player> responseEntity = null;
		Player player = null;
		int playerId = Integer.parseInt(id);
		try{
			player = playerService.getPlayer(playerId);
			if(player != null) {
				responseEntity = new ResponseEntity<Player>(player, HttpStatus.OK);
			}else{
				responseEntity = new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
