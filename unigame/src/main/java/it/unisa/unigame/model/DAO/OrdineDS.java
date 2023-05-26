package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Date;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.OrdineBean;
import it.unisa.unigame.model.interfaceDS.Ordine;

public class OrdineDS implements Ordine{
	
	static final String TABLE_NAME = "cliente";
	
	private DataSource ds = null;
	
	public OrdineDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(OrdineBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO" + 	ClienteDS.TABLE_NAME
				+ " (ID, CLIENTE, DATA_E_ORA, IMPORTO_TOTALE, NUM_CARTA) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getCodice_fiscale());
			preparedStmt.setByte(3, bean.getData_e_ora());
			preparedStmt.setFloat(4, bean.getImporto_totale());
			preparedStmt.setLong(5, bean.getNum_carta());
			
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
	public void doUpdate(OrdineBean order, String cf, int id, long carta, float importo, LocalDateTime data_e_ora) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ClienteDS.TABLE_NAME
				+ "SET ID = ?, CLIENTE = ?, DATA_E_ORA = ?, IMPORTO_TOTALE = ?, WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, cf);
			preparedStmt.setDate(3, data_e_ora);
			preparedStmt.setFloat(4, importo);
			preparedStmt.setLong(5, carta);
			
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
		
		String deleteSQL = "DELETE FROM " + ClienteDS.TABLE_NAME
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
	public OrdineBean doRetrieveByKey(int id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		OrdineBean bean = new OrdineBean();
		
		String selectSQL = "SELECT * FROM " + ClienteDS.TABLE_NAME
				+ "WHERE ID =  ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCodice_fiscale(rs.getString("cliente"));
				bean.setDate(rs.getString("data_e_ora"));
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setNum_carta(rs.getLong("num_carta"));
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
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
		
		Collection<OrdineBean> ordini = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setCodice_fiscale(rs.getString("cliente"));
				bean.setDate(rs.getString("data_e_ora"));
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setNum_carta(rs.getLong("num_carta"));
				
				ordini.add(bean);
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
		return ordini;
		
	}
}

