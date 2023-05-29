package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.GestoreAssistenzaBean;
import it.unisa.unigame.model.interfaceDS.GestoreAssistenza;

public class GestoreAssistenzaDS implements GestoreAssistenza {
	
private static final String TABLE_NAME = "gestore_assistenza";
	
	private DataSource ds = null;
	
	public GestoreAssistenzaDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public void doSave(GestoreAssistenzaBean gest) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + GestoreAssistenzaDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, USERNAME, EMAIL, PASS_WORD, RUOLO, RETRIBUZIONE_ANNUALE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setString(1, gest.getCodice_fiscale());
			preparedStmt.setString(2, gest.getNome());
			preparedStmt.setString(3, gest.getCognome());
			preparedStmt.setString(4, gest.getUsername());
			preparedStmt.setString(5, gest.getEmail());
			preparedStmt.setString(6, gest.getPassword());
			preparedStmt.setString(7, gest.getRuolo());
			preparedStmt.setInt(8, gest.getRetribuzione_annuale());
			
			preparedStmt.executeQuery();
			
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
	public void doUpdate(GestoreAssistenzaBean bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + GestoreAssistenzaDS.TABLE_NAME
				+ "SET NOME = ?, COGNOME = ?, USERNAME = ?, EMAIL = ?, PASS_WORD = ?, RETRIBUZIONE_ANNUALE = ?"
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
		
		String deleteSQL = "DELETE FROM " + GestoreAssistenzaDS.TABLE_NAME
				+ " WHERE USERNAME = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setString(1, username);
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
	public GestoreAssistenzaBean doRetrieveByKey(String username) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + GestoreAssistenzaDS.TABLE_NAME 
				+ " WHERE USERNAME = ?";
		
		GestoreAssistenzaBean bean = new GestoreAssistenzaBean();
		
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
	public GestoreAssistenzaBean doRetrieveByKeyEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + GestoreAssistenzaDS.TABLE_NAME 
				+ " WHERE EMAIL = ?";
		
		GestoreAssistenzaBean bean = new GestoreAssistenzaBean();
		
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
	public Collection<GestoreAssistenzaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		Collection<GestoreAssistenzaBean> gestori = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM " + GestoreAssistenzaDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				GestoreAssistenzaBean bean = new GestoreAssistenzaBean();
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("pass_word"));
				bean.setRetribuzione_annuale(rs.getInt("retribuzione_annuale"));
				
				gestori.add(bean);
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
		return gestori;
	}
}