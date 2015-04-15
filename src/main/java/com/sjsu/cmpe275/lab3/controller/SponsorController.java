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

import com.sjsu.cmpe275.lab3.model.Sponsor;
import com.sjsu.cmpe275.lab3.service.SponsorService;

@Controller
public class SponsorController {

	@Autowired
	private SponsorService sponsorService;

	@RequestMapping(value = "/sponsor/create", method = RequestMethod.POST, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Sponsor> createSponsor(
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("description") String description) {
		ResponseEntity<Sponsor> responseEntity = null;
		Sponsor sponsor = new Sponsor();

		sponsor.setName(name);
		sponsor.setAddress(address);
		sponsor.setDescription(description);
		try {
			Sponsor toBeSponsor = sponsorService.createSponsor(sponsor);
			if (toBeSponsor != null) {
				responseEntity = new ResponseEntity<Sponsor>(toBeSponsor,
						HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<Sponsor>(
						HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Sponsor> getSponsor(@PathVariable("id") String id) {
		ResponseEntity<Sponsor> responseEntity = null;
		Sponsor sponsor = null;
		int sponsorId = Integer.parseInt(id);
		try {
			sponsor = sponsorService.getSponsor(sponsorId);
			if (sponsor != null) {
				responseEntity = new ResponseEntity<Sponsor>(sponsor,
						HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<Sponsor>(
						HttpStatus.NOT_FOUND);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/sponsor/update/{id}", method = RequestMethod.POST, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Sponsor> updateSponsor(@PathVariable("id") int id,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("description") String description) {
		ResponseEntity<Sponsor> responseEntity = null;
		Sponsor sponsor = new Sponsor();

		sponsor.setId(id);
		sponsor.setName(name);
		sponsor.setAddress(address);
		sponsor.setDescription(description);
		try {
			Sponsor toBeUpdated = sponsorService.getSponsor(id);
			if (toBeUpdated == null) {
				responseEntity = new ResponseEntity<Sponsor>(
						HttpStatus.NOT_FOUND);
			}
			toBeUpdated = sponsorService.updateSponsor(id, sponsor);
			if (toBeUpdated == null) {
				responseEntity = new ResponseEntity<Sponsor>(
						HttpStatus.BAD_REQUEST);
			} else {
				responseEntity = new ResponseEntity<Sponsor>(toBeUpdated,
						HttpStatus.OK);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/sponsor/delete/{id}", method = RequestMethod.DELETE, headers = { "Accept=text/xml, application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Sponsor> deleteSponsor(@PathVariable("id") int id) {
		ResponseEntity<Sponsor> responseEntity = null;
		try {
			Sponsor toBeDeleted = sponsorService.getSponsor(id);
			if (toBeDeleted == null) {
				responseEntity = new ResponseEntity<Sponsor>(
						HttpStatus.NOT_FOUND);
			} else {
				toBeDeleted = sponsorService.deleteSponsor(id);
				responseEntity = new ResponseEntity<Sponsor>(toBeDeleted,
						HttpStatus.OK);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}
