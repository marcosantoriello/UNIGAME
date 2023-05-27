package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.ComprendeOPBean;
import it.unisa.unigame.model.interfaceDS.ComprendeOP;

public class ComprendeOPDS implements ComprendeOP{
	
	static final String TABLE_NAME= "comprende_op";
	
	private DataSource ds=null;
	
	public ComprendeOPDS(DataSource ds) {
		this.ds=ds;
	}
	

	@Override
	public void doSave(ComprendeOPBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ ComprendeOPDS.TABLE_NAME + "(PRODOTTO, ORDINE)"+ "VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getProdotto());
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
	public void doUpdate(ComprendeOPBean comprendeOP, int id_prodotto, int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ComprendeOPDS.TABLE_NAME
				+ "SET PRODOTTO = ?, ORDINE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, id_prodotto);
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
	public boolean doDelete(int id_prodotto, int id_ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ComprendeOPDS.TABLE_NAME
				+ "WHERE PRODOTTO = ? AND ORDINE= ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setInt(1, id_prodotto);
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
	public ComprendeOPBean doRetrieveByKey(int id_prodotto, int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ComprendeOPBean bean = new ComprendeOPBean();
		
		String selectSQL = "SELECT * FROM " + ComprendeOPDS.TABLE_NAME
				+ "WHERE PRODOTTO =  ? AND ORDINE=?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id_prodotto);
			preparedStmt.setInt(2, id_ordine);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setProdotto(rs.getInt("prodotto"));
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
	public Collection<ComprendeOPBean> doRetrieveAll(String order) throws SQLException {

		Collection<ComprendeOPBean> ordine_prodottoF = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + ComprendeOPDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ComprendeOPBean bean = new ComprendeOPBean();
				
				bean.setProdotto(rs.getInt("prodotto"));
				bean.setOrdine(rs.getInt("ordine"));
				
				ordine_prodottoF.add(bean);
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
		return ordine_prodottoF;	
		}
}