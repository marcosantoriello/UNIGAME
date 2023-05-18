package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public void doUpdate(SoftwareHouseBean soft) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		String updateSQL = "UPDATE " + TABLE_NAME 
			+ "SET LOCAZIONE = ?, ANNO_FONDAZIONE = ? WHERE NOME = ?";
		
		
	}

	@Override
	public boolean doDelete(String nome) throws SQLException {
		
		return false;
	}

	@Override
	public SoftwareHouseBean doRetrieveByKey(String nome) throws SQLException {
		
		return null;
	}

	@Override
	public Collection<SoftwareHouseBean> doRetrieveAll(String order) throws SQLException {
		
		return null;
	}

}
