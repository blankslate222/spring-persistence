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

	@RequestMapping(value = "/player/create", method = RequestMethod.POST, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> createPlayer(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "sponsor", required = false) String sponsor,
			@RequestParam(value = "description", required = false) String description) {
		ResponseEntity<Player> responseEntity = null;
		Player player = new Player();
		Sponsor spnsr = new Sponsor();
		if (null != sponsor) {
			spnsr.setId(Integer.parseInt(sponsor));
		} else {
			spnsr.setId(0);
		}
		player.setFirstName(firstName);
		player.setLastName(lastName);
		player.setEmail(email);
		player.setAddress(address);
		player.setSponsor(spnsr);
		player.setDescription(description);
		try {
			Player toBePlayer = playerService.createPlayer(player);
			if (toBePlayer != null) {
				responseEntity = new ResponseEntity<Player>(toBePlayer,
						HttpStatus.OK);
				// System.out.println("ctrllr");
			} else {
				responseEntity = new ResponseEntity<Player>(
						HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/player/{id}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> getPlayer(@PathVariable("id") String id,
			Model model) {
		ResponseEntity<Player> responseEntity = null;
		Player player = null;
		int playerId = Integer.parseInt(id);
		try {
			player = playerService.getPlayer(playerId);
			if (player != null) {
				responseEntity = new ResponseEntity<Player>(player,
						HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<Player>(
						HttpStatus.NOT_FOUND);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/player/update/{id}", method = RequestMethod.POST, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> updatePlayer(
			@PathVariable("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "sponsor", required = false) String sponsor,
			@RequestParam(value = "description", required = false) String description) {
		ResponseEntity<Player> responseEntity = null;
		Player player = new Player();
		Sponsor spnsr = new Sponsor();
		if (null != sponsor) {
			spnsr.setId(Integer.parseInt(sponsor));
		} else {
			spnsr.setId(0);
		}

		player.setId(id);
		player.setFirstName(firstName);
		player.setLastName(lastName);
		player.setEmail(email);
		player.setAddress(address);
		player.setSponsor(spnsr);
		player.setDescription(description);
		try {
			Player toBeUpdated = playerService.getPlayer(id);
			if (toBeUpdated == null) {
				responseEntity = new ResponseEntity<Player>(
						HttpStatus.NOT_FOUND);
			}else{
			toBeUpdated = playerService.updatePlayer(id, player);
			if (toBeUpdated == null) {
				responseEntity = new ResponseEntity<Player>(
						HttpStatus.BAD_REQUEST);
			} else {
				responseEntity = new ResponseEntity<Player>(toBeUpdated,
						HttpStatus.OK);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/player/delete/{id}", method = RequestMethod.DELETE, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Player> deletePlayer(@PathVariable("id") int id) {
		ResponseEntity<Player> responseEntity = null;
		try {
			Player toBeDeleted = playerService.getPlayer(id);
			if (toBeDeleted == null) {
				responseEntity = new ResponseEntity<Player>(
						HttpStatus.NOT_FOUND);
			} else {
				toBeDeleted = playerService.deletePlayer(id);
				responseEntity = new ResponseEntity<Player>(toBeDeleted,
						HttpStatus.OK);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}
