package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.ComprendeOVBean;
import it.unisa.unigame.model.interfaceDS.ComprendeOV;

public class ComprendeOVDS implements ComprendeOV{
	
	static final String TABLE_NAME= "comprende_ov";
	
	private DataSource ds=null;
	
	public ComprendeOVDS(DataSource ds) {
		this.ds=ds;
	}
	

	@Override
	public void doSave(ComprendeOVBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ ComprendeOVDS.TABLE_NAME + "(VIDEOGIOCO, ORDINE)"+ "VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getVideogioco());
			preparedStmt.setInt(2, bean.getOrdine());

			
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
	public void doUpdate(ComprendeOVBean comprendeOP, int id_videogioco, int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ComprendeOVDS.TABLE_NAME
				+ "SET VIDEOGIOCO = ?, ORDINE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, id_videogioco);
			preparedStmt.setInt(2, id_ordine);
			
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
	public boolean doDelete(int id_videogioco, int id_ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ComprendeOVDS.TABLE_NAME
				+ "WHERE VIDEOGIOCO = ? AND ORDINE= ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setInt(1, id_videogioco);
			preparedStmt.setInt(2, id_ordine);
			
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
	public ComprendeOVBean doRetrieveByKey(int id_videogioco, int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ComprendeOVBean bean = new ComprendeOVBean();
		
		String selectSQL = "SELECT * FROM " + ComprendeOVDS.TABLE_NAME
				+ "WHERE VIDEOGIOCO =  ? AND ORDINE=?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id_videogioco);
			preparedStmt.setInt(2, id_ordine);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setVideogioco(rs.getInt("videogioco"));
				bean.setOrdine(rs.getInt("ordine"));
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
	public Collection<ComprendeOVBean> doRetrieveAll(String order) throws SQLException {

		Collection<ComprendeOVBean> ordine_videogioco = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + ComprendeOVDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ComprendeOVBean bean = new ComprendeOVBean();
				
				bean.setVideogioco(rs.getInt("videogioco"));
				bean.setOrdine(rs.getInt("ordine"));
				
				ordine_videogioco.add(bean);
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
		return ordine_videogioco;	
	}
}