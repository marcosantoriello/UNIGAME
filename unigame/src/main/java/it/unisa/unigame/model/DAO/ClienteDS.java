package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Date;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.ClienteBean;
import it.unisa.unigame.model.interfaceDS.Cliente;

public class ClienteDS implements Cliente{
	
	private static final String TABLE_NAME = "cliente";
	
	private DataSource ds = null;
	
	public ClienteDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(ClienteBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO" + 	ClienteDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, USERNAME, EMAIL, PASS_WORD, RUOLO, IND_FATTURAZIONE, DATA_DI_NASCITA, CONTA_ORDINI_REL, SOSPESO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setString(1, bean.getCodice_fiscale());
			preparedStmt.setString(2, bean.getNome());
			preparedStmt.setString(3, bean.getCognome());
			preparedStmt.setString(4, bean.getUsername());
			preparedStmt.setString(5, bean.getEmail());
			preparedStmt.setString(6, bean.getPassword());
			preparedStmt.setString(7, bean.getRuolo());
			preparedStmt.setString(8, bean.getInd_fatturazione());
			preparedStmt.setDate(9, Date.valueOf(bean.getData_di_nascita()));
			preparedStmt.setInt(10, bean.getConta_ordine_rel());
			preparedStmt.setBoolean(11, bean.isSospeso());
			
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
	public void doUpdate(ClienteBean cliente, String cf, String nome, String cognome, String username, String email,
			String password, String indirizzo_fatturazione, LocalDate data_di_nascita, boolean sospeso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ClienteDS.TABLE_NAME
				+ "SET NOME = ?, COGNOME = ?, USERNAME = ?, EMAIL = ?, PASS_WORD = ?, INDIRIZZO_FATTURAZIONE = ?," 
				+ " DATA_DI_NASCITA = ?, SOSPESO = ? WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setString(1, nome);
			preparedStmt.setString(2, cognome);
			preparedStmt.setString(3, username);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, password);
			preparedStmt.setString(6, indirizzo_fatturazione);
			preparedStmt.setDate(7, Date.valueOf(data_di_nascita));
			preparedStmt.setBoolean(8, sospeso);
			preparedStmt.setString(9, cf);
			
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
		
		String deleteSQL = "DELETE FROM " + ClienteDS.TABLE_NAME
				+ "WHERE USERNAME = ?";
		
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
	public ClienteBean doRetrieveByKey(String username) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ClienteBean bean = new ClienteBean();
		
		String selectSQL = "SELECT * FROM " + ClienteDS.TABLE_NAME
				+ "WHERE USERNAME =  ?";
		
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
				bean.setInd_fatturazione(rs.getString("indirizzo_fatturazione"));
				bean.setData_di_nascita(rs.getDate("data_di_nascita").toLocalDate());
				bean.setSospeso(rs.getBoolean("sospeso"));
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
	public ClienteBean doRetrieveByKeyEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ClienteBean bean = new ClienteBean();
		
		String selectSQL = "SELECT * FROM " + ClienteDS.TABLE_NAME
				+ "WHERE EMAIL =  ?";
		
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
				bean.setInd_fatturazione(rs.getString("indirizzo_fatturazione"));
				bean.setData_di_nascita(rs.getDate("data_di_nascita").toLocalDate());
				bean.setSospeso(rs.getBoolean("sospeso"));
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
	public Collection<ClienteBean> doRetrieveAll(String order) throws SQLException {
		
		Collection<ClienteBean> clienti = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + ClienteDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ClienteBean bean = new ClienteBean();
				
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("pass_word"));
				bean.setInd_fatturazione(rs.getString("indirizzo_fatturazione"));
				bean.setData_di_nascita(rs.getDate("data_di_nascita").toLocalDate());
				bean.setSospeso(rs.getBoolean("sospeso"));
				
				clienti.add(bean);
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
		return clienti;
		
	}

}