package com.sjsu.cmpe275.lab3.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjsu.cmpe275.lab3.dao.PlayerDao;
import com.sjsu.cmpe275.lab3.model.Player;
import com.sjsu.cmpe275.lab3.model.Sponsor;

public class PlayerDaoImpl implements PlayerDao {

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int addPlayer(Player player) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int insert = 0;

		String sql = "insert into player"
				+ "(firstName, lastName, email, address, sponsor, description) "
				+ " values(?,?,?,?,?,?)";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, player.getFirstName());
		ps.setString(2, player.getLastName());
		ps.setString(3, player.getEmail());
		ps.setString(4, player.getAddress());
		ps.setInt(5, player.getSponsor().getId());
		ps.setString(6, player.getDescription());
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

	public int updatePlayer(int playerId, Player player) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "update player set  firstName = ?, lastName =?, email = ?,"
				+ " address = ?, sponsor = ?, description = ? where id = ? ";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, player.getFirstName());
		ps.setString(2, player.getLastName());
		ps.setString(3, player.getEmail());
		ps.setString(4, player.getAddress());
		ps.setInt(5, player.getSponsor().getId());
		ps.setString(6, player.getDescription());
		ps.setInt(7, playerId);
		
		int response = ps.executeUpdate();
		
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return playerId;
	}

	public int deletePlayer(int playerId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from player where id = ?";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, playerId);
		ps.executeUpdate();
		
		return playerId;
	}

	public Player getPlayer(int playerId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int insert = 0;

		String sql = "select * from player where id = ?";

		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, playerId);
		ResultSet rs = ps.executeQuery();
		Player player = null;
		Sponsor sponsor = null;
		if (rs.next()) {
			player = new Player();
			sponsor = new Sponsor();
			player.setId(rs.getInt("id"));
			player.setEmail(rs.getString("email"));
			player.setFirstName(rs.getString("firstName"));
			player.setLastName(rs.getString("lastName"));
			player.setAddress(rs.getString("address"));
			player.setDescription(rs.getString("description"));
			sponsor.setId(Integer.parseInt(rs.getString("sponsor")));
			player.setSponsor(sponsor);
		}
		try {
			ps.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

}
