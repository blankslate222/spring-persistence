package com.sjsu.cmpe275.lab3.dao;

import java.sql.SQLException;

import com.sjsu.cmpe275.lab3.model.Sponsor;

public interface SponsorDao {

	public void createSponsor(Sponsor sponsor) throws SQLException;
	public Sponsor getSponsor(int id) throws SQLException;
	public void updateSponsor(int sponsorId, Sponsor sponsor) throws SQLException;
	public void deleteSponsor(int id) throws SQLException;
	
}
