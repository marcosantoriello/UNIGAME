package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.AmministratoreBean;
import it.unisa.unigame.model.interfaceDS.Amministratore;

public class AmministratoreDS implements Amministratore {
	
	private static final String TABLE_NAME = "amministratore";
	
	private DataSource ds = null;
	
	public AmministratoreDS(DataSource ds) {
		this.ds = ds;
	}
	
	public void doSave(AmministratoreBean amm) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO" + AmministratoreDS.TABLE_NAME 
				+ " (CODICE_FISCALE, NOME, COGNOME, USERNAME, EMAIL, PASS_WORD, RUOLO, RETRIBUZIONE_ANNUALE) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, amm.getCodice_fiscale());
			preparedStmt.setString(2, amm.getNome());
			preparedStmt.setString(3, amm.getCognome());
			preparedStmt.setString(4, amm.getUsername());
			preparedStmt.setString(5, amm.getEmail());
			preparedStmt.setString(6, amm.getPassword());
			preparedStmt.setString(7, amm.getRuolo());
			preparedStmt.setInt(8, amm.getRetribuzione_annuale());
			
			preparedStmt.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public void doUpdate(AmministratoreBean bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AmministratoreDS.TABLE_NAME
				+ " SET NOME = ?, COGNOME = ?, USERNAME = ?, EMAIL = ?, PASS_WORD = ?, RETRIBUZIONE_ANNUALE = ?"
				+ " WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			
			preparedStmt.setString(1, bean.getNome());
			preparedStmt.setString(2, bean.getCognome());
			preparedStmt.setString(3, bean.getUsername());
			preparedStmt.setString(4, bean.getEmail());
			preparedStmt.setString(5, bean.getPassword());
			preparedStmt.setInt(6, bean.getRetribuzione_annuale());
			preparedStmt.setString(7, bean.getCodice_fiscale());
			
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
	public boolean doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " +  AmministratoreDS.TABLE_NAME
				+ " WHERE USERNAME = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, deleteSQL);
			
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
	public AmministratoreBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + AmministratoreDS.TABLE_NAME
				+ " WHERE USERNAME = ?";
		
		AmministratoreBean bean = new AmministratoreBean();
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, username);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("pass_word"));
				bean.setRetribuzione_annuale(rs.getInt("retribuzione_annuale"));
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
	public AmministratoreBean doRetrieveByKeyEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + AmministratoreDS.TABLE_NAME
				+ " WHERE EMAIL = ?";
		
		AmministratoreBean bean = new AmministratoreBean();
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, email);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("pass_word"));
				bean.setRetribuzione_annuale(rs.getInt("retribuzione_annuale"));
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
	public Collection<AmministratoreBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + AmministratoreDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		Collection<AmministratoreBean> amministratori = new LinkedList<>();
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AmministratoreBean bean = new AmministratoreBean();
				
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setRetribuzione_annuale(rs.getInt("retribuzione_annuale"));
				
				amministratori.add(bean);
			}
		}
		
		finally {
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
			}
			finally {
				if (connection != null)
					connection.close();
			}	
			
		}
		
		return amministratori;
	}
}
