package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.TelefonoBean;
import it.unisa.unigame.model.interfaceDS.Telefono;

public class TelefonoDS implements Telefono {
	
private static final String TABLE_NAME = "telefono";
	
	private DataSource ds = null;
	
	public TelefonoDS(DataSource ds) {
		this.ds = ds;
	}
	
	public void doSave(TelefonoBean tel) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME + "(NUMERO, CLIENTE) "
				+ "VALUES(?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, tel.getNumero());
			preparedStmt.setString(2, tel.getCliente_cf());
			preparedStmt.execute();
			
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
	public void doUpdate(TelefonoBean tel) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		String updateSQL = "UPDATE " + TABLE_NAME + " SET CLIENTE = ? WHERE NUMERO = ?";
	
		   //il bean del telefono è quello nuovo	
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, tel.getCliente_cf());
			preparedStmt.setInt(2, tel.getNumero()); 
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
	public boolean doDelete(int num) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + TABLE_NAME 
				+ "WHERE NUMERO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, num);
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
	public TelefonoBean doRetrieveByKey(int num) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		TelefonoBean bean = new TelefonoBean();
		String selectSQL = "SELECT * FROM " + TABLE_NAME
				+ " WHERE NUMERO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, num);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setCliente_cf(rs.getString("cliente"));
				bean.setNumero(rs.getInt("numero"));
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
	public Collection<TelefonoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		Collection<TelefonoBean> tel = new LinkedList<>();
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStmt.executeQuery();
			while(rs.next()) {
				TelefonoBean bean = new TelefonoBean();
				bean.setCliente_cf(rs.getString("cliente"));
				bean.setNumero(rs.getInt("numero"));
				tel.add(bean);
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
		return tel;
	}
	
	
}
