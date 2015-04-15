package com.sjsu.cmpe275.lab3.dao;

import java.sql.SQLException;

import com.sjsu.cmpe275.lab3.model.Sponsor;

public interface SponsorDao {

	public int createSponsor(Sponsor sponsor) throws SQLException;
	public Sponsor getSponsor(int id) throws SQLException;
	public int updateSponsor(int sponsorId, Sponsor sponsor) throws SQLException;
	public int deleteSponsor(int id) throws SQLException;
	
}
