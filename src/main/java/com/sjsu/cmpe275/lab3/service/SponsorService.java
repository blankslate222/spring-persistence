package com.sjsu.cmpe275.lab3.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe275.lab3.daoimpl.SponsorDaoImpl;
import com.sjsu.cmpe275.lab3.model.Sponsor;

@Service
public class SponsorService {
	@Autowired
	private SponsorDaoImpl sponsorDaoImpl;
	
	public SponsorDaoImpl getSponsorDaoImpl() {
		return sponsorDaoImpl;
	}

	public void setSponsorDaoImpl(SponsorDaoImpl sponsorDaoImpl) {
		this.sponsorDaoImpl = sponsorDaoImpl;
	}
	
	public Sponsor createSponsor(Sponsor sponsor) throws SQLException {
		if("".equals(sponsor.getName())) {
			return null;
		}
		int newSponsorId = getSponsorDaoImpl().createSponsor(sponsor);
		return this.getSponsor(newSponsorId);
	}
	
	public Sponsor getSponsor(int id) throws SQLException {
		Sponsor sponsor = getSponsorDaoImpl().getSponsor(id);
		return sponsor;
	}
	
	public Sponsor updateSponsor(int id, Sponsor sponsor) throws SQLException{
		if(this.getSponsor(id) == null) {
			return null;
		}
		int updatedSponsorId = getSponsorDaoImpl().updateSponsor(id, sponsor);
		return this.getSponsor(updatedSponsorId);
	}
	
	public Sponsor deleteSponsor(int id) throws SQLException {
		Sponsor sponsorToBeDeleted = this.getSponsor(id);
		if(sponsorToBeDeleted == null) return null;
		int deletedSponsorId = getSponsorDaoImpl().deleteSponsor(id);
		return sponsorToBeDeleted;
	}
}
