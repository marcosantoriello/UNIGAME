package it.unisa.unigame.model.DAO;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.OrdineBean;
import it.unisa.unigame.model.interfaceDS.Ordine;

public class OrdineDS implements Ordine{
	
	static final String TABLE_NAME = "ordine";
	
	private DataSource ds = null;
	
	public OrdineDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void doSave(OrdineBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO" + OrdineDS.TABLE_NAME
				+ " (ID, CLIENTE, DATA_E_ORA, IMPORTO_TOTALE, NUM_CARTA, FATTURA) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getCodice_fiscale());
			preparedStmt.setTimestamp(3, Timestamp.valueOf(bean.getData_e_ora()));
			preparedStmt.setFloat(4, bean.getImporto_totale());
			preparedStmt.setLong(5, bean.getNum_carta());
			preparedStmt.setBoolean(10, bean.isFattura());	
			
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
	public void doUpdate(OrdineBean bean) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + OrdineDS.TABLE_NAME
				+ "SET ID = ?, CLIENTE = ?, DATA_E_ORA = ?, IMPORTO_TOTALE = ?, CARTA= ?,FATTURA= ?, WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getCodice_fiscale());
			preparedStmt.setTimestamp(3, Timestamp.valueOf(bean.getData_e_ora()));
			preparedStmt.setFloat(4, bean.getImporto_totale());
			preparedStmt.setLong(5, bean.getNum_carta());
			preparedStmt.setBoolean(10, bean.isFattura());		
			
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
		
		String deleteSQL = "DELETE FROM " + OrdineDS.TABLE_NAME
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
		
		String selectSQL = "SELECT * FROM " + OrdineDS.TABLE_NAME
				+ "WHERE ID =  ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCodice_fiscale(rs.getString("cliente"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setNum_carta(rs.getLong("num_carta"));
				bean.setFattura(rs.getBoolean("fattura"));	
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
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setNum_carta(rs.getLong("num_carta"));
				bean.setFattura(rs.getBoolean("fattura"));
				
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

