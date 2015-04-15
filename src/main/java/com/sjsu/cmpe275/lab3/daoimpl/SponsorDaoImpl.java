package com.sjsu.cmpe275.lab3.daoimpl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjsu.cmpe275.lab3.dao.SponsorDao;
import com.sjsu.cmpe275.lab3.model.Sponsor;

public class SponsorDaoImpl implements SponsorDao {

	@Autowired
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void createSponsor(Sponsor sponsor) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Sponsor getSponsor(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateSponsor(int sponsorId, Sponsor sponsor)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	public void deleteSponsor(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
