package com.sjsu.cmpe275.lab3.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjsu.cmpe275.lab3.dao.OpponentDao;

public class OpponentDaoImpl implements OpponentDao {

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void addOpponent(int playerId1, int playerId2) throws SQLException{
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int insert = 0;

		String sql = "insert into opponent"
				+ "(player1, player2) values(?,?)";

			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, playerId1);
			ps.setInt(2, playerId2);
			insert = ps.executeUpdate();
	//set others
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

	public void removeOpponent(int playerId1, int playerId2) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int delete = 0;

		String sql = "delete from opponent where player1 = ? and player2 = ?";

			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, playerId1);
			ps.setInt(2, playerId2);
			delete = ps.executeUpdate();

			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}


	public List<String> getOpponents(int playerId) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		List<String> opponents = new ArrayList<String>();
		String sql = "select * from opponent where player1 = ? || player2 = ?";
		conn = getDataSource().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, "" + playerId);
		ps.setString(2, "" + playerId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String opponent = (rs.getString("player1").equals("" + playerId) ? rs
					.getString("player2") : rs.getString("player1"));
			opponents.add(opponent);
		}
		return opponents;
	}

}
