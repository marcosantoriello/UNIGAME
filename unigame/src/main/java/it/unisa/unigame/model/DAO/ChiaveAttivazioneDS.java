package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.ChiaveAttivazioneBean;
import it.unisa.unigame.model.interfaceDS.ChiaveAttivazione;

public class ChiaveAttivazioneDS implements ChiaveAttivazione{
	
	static final String TABLE_NAME= "chiave_attivazione";
	
	private DataSource ds=null;
	
	public ChiaveAttivazioneDS(DataSource ds) {
		this.ds=ds;
	}

	@Override
	public void doSave(ChiaveAttivazioneBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ ChiaveAttivazioneDS.TABLE_NAME + "(CHIAVE, VIDEOGIOCO)"+ "VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setLong(1, bean.getChiave());
			preparedStmt.setInt(2, bean.getVideogioco());
			
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
	public void doUpdate(ChiaveAttivazioneBean recensione, long chiave, int videogame) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ChiaveAttivazioneDS.TABLE_NAME
				+ "SET CHIAVE= ?, VIDEOGIOCO= ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setLong(1, chiave);
			preparedStmt.setInt(2, videogame);
			
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
	public boolean doDelete(long chiave) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ChiaveAttivazioneDS.TABLE_NAME
				+ "WHERE CHIAVE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setLong(1, chiave);
			
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
	public ChiaveAttivazioneBean doRetrieveByKey(long chiave) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ChiaveAttivazioneBean bean = new ChiaveAttivazioneBean();
		
		String selectSQL = "SELECT * FROM " + ChiaveAttivazioneDS.TABLE_NAME
				+ "WHERE VIDEOGIOCO =  ? AND ORDINE=?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setLong(1,chiave);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setChiave(rs.getLong("chiave"));
				bean.setVideogioco(rs.getInt("videogioco"));
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
	public Collection<ChiaveAttivazioneBean> doRetrieveAll(String order) throws SQLException {
		Collection<ChiaveAttivazioneBean> chiavi = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + RecensioneDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ChiaveAttivazioneBean bean = new ChiaveAttivazioneBean();
				
				bean.setChiave(rs.getLong("chiave"));
				bean.setVideogioco(rs.getInt("videogioco"));
				
				chiavi.add(bean);
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
		return chiavi;	
	}
	
}