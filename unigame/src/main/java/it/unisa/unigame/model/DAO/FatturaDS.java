package it.unisa.unigame.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.FatturaBean;
import it.unisa.unigame.model.interfaceDS.Fattura;

public class FatturaDS implements Fattura{
	
	static final String TABLE_NAME= "fattura";
	
	private DataSource ds=null;

	public FatturaDS(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public void doSave(FatturaBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ FatturaDS.TABLE_NAME + "(ID, ORDINE, IMPORTO_TOTALE, DATA_E_ORA)"+ "VALUES (?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setInt(2, bean.getOrdine());
			preparedStmt.setFloat(3, bean.getImporto_totale());
			preparedStmt.setTimestamp(4, Timestamp.valueOf(bean.getData_e_ora()));
			
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
	public void doUpdate(FatturaBean recensione,int id, int ordine, float importo, LocalDateTime data_e_ora) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + FatturaDS.TABLE_NAME
				+ "SET ID= ?, ORDINE= ?, IMPORTO_TOTALE= ?, DATA_E_ORA= ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, id);
			preparedStmt.setInt(2, ordine);
			preparedStmt.setFloat(3, importo);
			preparedStmt.setTimestamp(4, Timestamp.valueOf(data_e_ora));
			
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
		
		String deleteSQL = "DELETE FROM " + FatturaDS.TABLE_NAME
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
	public FatturaBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		FatturaBean bean = new FatturaBean();
		
		String selectSQL = "SELECT * FROM " + FatturaDS.TABLE_NAME
				+ "WHERE ID =  ? ";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setOrdine(rs.getInt("ordine"));
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
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
	public Collection<FatturaBean> doRetrieveAll(String order) throws SQLException {
		Collection<FatturaBean> fatture = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + FatturaDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				FatturaBean bean = new FatturaBean();
				
				bean.setId(rs.getInt("id"));
				bean.setOrdine(rs.getInt("ordine"));
				bean.setImporto_totale(rs.getFloat("importo_totale"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				
				fatture.add(bean);
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
		return fatture;	
	}
}