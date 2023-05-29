package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.RecensioneBean;
import it.unisa.unigame.model.bean.RecensioneBean.Indice_gradimento;
import it.unisa.unigame.model.interfaceDS.Recensione;

public class RecensioneDS implements Recensione{
	
	static final String TABLE_NAME= "recensione";
	
	private DataSource ds=null;
	
	public RecensioneDS(DataSource ds) {
		this.ds=ds;
	}
	

	@Override
	public void doSave(RecensioneBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ RecensioneDS.TABLE_NAME + "(ID, CLIENTE, PRODOTTO, VIDEOGIOCO, DATA_E_ORA, DESCRIZIONE, INDICE_DI_GRADIMENTO)"+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getVideogioco());
			preparedStmt.setString(2, bean.getCliente());
			preparedStmt.setInt(3, bean.getProdotto());
			preparedStmt.setInt(4, bean.getVideogioco());
			preparedStmt.setTimestamp(5, Timestamp.valueOf(bean.getData_e_ora()));
			preparedStmt.setString(6, bean.getDescrizione());
			preparedStmt.setString(7, bean.getIndice_di_gradimento().name());
			
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
	public void doUpdate(RecensioneBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + RecensioneDS.TABLE_NAME
				+ "SET ID= ?, CLIENTE= ?, PRODOTTO= ?, VIDEOGIOCO= ?, DATA_E_ORA= ?, DESCRIZIONE= ?, INDICE_DI_GRADIMENTO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getCliente());
			preparedStmt.setInt(3, bean.getProdotto());
			preparedStmt.setInt(4, bean.getVideogioco());
			preparedStmt.setTimestamp(5, Timestamp.valueOf(bean.getData_e_ora()));
			preparedStmt.setString(6, bean.getDescrizione());
			preparedStmt.setString(7, bean.getIndice_di_gradimento().name());
			
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
	public boolean doDelete(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + RecensioneDS.TABLE_NAME
				+ "WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setInt(1, id);
			
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
	public RecensioneBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		RecensioneBean bean = new RecensioneBean();
		
		String selectSQL = "SELECT * FROM " + RecensioneDS.TABLE_NAME
				+ "WHERE VIDEOGIOCO =  ? AND ORDINE=?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCliente(rs.getString("cliente"));
				bean.setProdotto(rs.getInt("prodotto"));
				bean.setVideogioco(rs.getInt("videogioco"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setIndice_di_gradimento(Indice_gradimento.valueOf(rs.getString("indice_di_gradimento")));			}
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
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException {

		Collection<RecensioneBean> recensioni = new LinkedList<>();
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
				RecensioneBean bean = new RecensioneBean();
				
				bean.setId(rs.getInt("id"));
				bean.setCliente(rs.getString("cliente"));
				bean.setProdotto(rs.getInt("prodotto"));
				bean.setVideogioco(rs.getInt("videogioco"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setIndice_di_gradimento(Indice_gradimento.valueOf(rs.getString("indice_di_gradimento")));
				
				recensioni.add(bean);
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
		return recensioni;	
	}
}