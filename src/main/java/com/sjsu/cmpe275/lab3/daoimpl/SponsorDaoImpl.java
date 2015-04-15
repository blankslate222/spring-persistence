package com.sjsu.cmpe275.lab3.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjsu.cmpe275.lab3.dao.SponsorDao;
import com.sjsu.cmpe275.lab3.model.Player;
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

	public int createSponsor(Sponsor sponsor) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int insert = 0;

		String sql = "insert into sponsor"
				+ "(name, address,description) "
				+ " values(?,?,?)";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, sponsor.getName());
		ps.setString(2, sponsor.getAddress());
		ps.setString(3, sponsor.getDescription());
		// set others
		insert = ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int rowid = 0;
		if (rs.next()) {
			System.out.println(rs.toString());
			rowid = rs.getInt(1);
		}
		try {
			ps.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (rowid);
	}

	public Sponsor getSponsor(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "select * from sponsor where id = ?";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Sponsor sponsor = null;
		if (rs.next()) {
			sponsor = new Sponsor();
			sponsor.setId(rs.getInt("id"));
			sponsor.setName(rs.getString("name"));
			sponsor.setAddress(rs.getString("address"));
			sponsor.setDescription(rs.getString("description"));
		}
		try {
			ps.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sponsor;
	}

	public int updateSponsor(int sponsorId, Sponsor sponsor)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "update sponsor set  name = ?, address =?,"
				+ " description = ? where id = ? ";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, sponsor.getName());
		ps.setString(2, sponsor.getAddress());
		ps.setString(3, sponsor.getDescription());
		ps.setInt(4, sponsorId);
		
		int response = ps.executeUpdate();
		
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sponsorId;
	}

	public int deleteSponsor(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from sponsor where id = ?";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		return id;
	}

}
