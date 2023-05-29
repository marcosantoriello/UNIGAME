 package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.SoftwareHouseBean;
import it.unisa.unigame.model.interfaceDS.SoftwareHouse;

public class SoftwareHouseDS implements SoftwareHouse{
	
	private static final String TABLE_NAME = "software_house";
	
	private DataSource ds = null;
	
	public SoftwareHouseDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(SoftwareHouseBean soft) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ "(NOME, LOCAZIONE, ANNO_FONDAZIONE) VALUES(?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, soft.getNome());
			preparedStmt.setString(2, soft.getLocazione());
			preparedStmt.setDate(3, java.sql.Date.valueOf(soft.getFondazione()));
			
			preparedStmt.executeUpdate();
			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}

	@Override
	public void doUpdate(SoftwareHouseBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + SoftwareHouseDS.TABLE_NAME
				+ "SET NOME= ?, LOCAZIONE= ?, ANNO_FONDAZIONE= ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setString(1, bean.getNome());
			preparedStmt.setString(2, bean.getLocazione());
			preparedStmt.setTimestamp(3, Timestamp.valueOf(bean.getFondazione().atTime(LocalTime.MIDNIGHT)));
			
			preparedStmt.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
		}
		
		finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}	
		
	}

	@Override
	public boolean doDelete(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + SoftwareHouseDS.TABLE_NAME
				+ "WHERE NOME = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setString(1, nome);
			
			result = preparedStmt.executeUpdate();
		}
		finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public SoftwareHouseBean doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		SoftwareHouseBean bean = new SoftwareHouseBean();
		
		String selectSQL = "SELECT * FROM " + SoftwareHouseDS.TABLE_NAME
				+ "WHERE NOME =  ? ";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, nome);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setLocazione(rs.getString("locazione"));
				bean.setFondazione(rs.getTimestamp("anno_fondazione").toLocalDateTime().toLocalDate());
			}
		}
		
		finally {
			
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public Collection<SoftwareHouseBean> doRetrieveAll(String order) throws SQLException {
		Collection<SoftwareHouseBean> houses = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + SoftwareHouseDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				SoftwareHouseBean bean = new SoftwareHouseBean();
				

				bean.setNome(rs.getString("nome"));
				bean.setLocazione(rs.getString("locazione"));
				bean.setFondazione(rs.getTimestamp("anno_fondazione").toLocalDateTime().toLocalDate());
				
				houses.add(bean);
			}
		}
		finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		return houses;	
	}

}
