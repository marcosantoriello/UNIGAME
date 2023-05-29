package it.unisa.unigame.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.ProdottoFisicoBean;
import it.unisa.unigame.model.interfaceDS.ProdottoFisico;


public class ProdottoFisicoDS implements ProdottoFisico{
	
	static final String  TABLE_NAME= "prodotto_fisico";
	
	private DataSource ds=null;
	
	public ProdottoFisicoDS(DataSource ds) {
		this.ds=ds;
	}

	@Override
	public void doSave(ProdottoFisicoBean bean) throws SQLException {
		
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ ProdottoFisicoDS.TABLE_NAME + "(ID,NOME, PREZZO, QUANTITà, DISPONIBILE)"+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getNome());
			preparedStmt.setFloat(3, bean.getPrezzo());
			preparedStmt.setInt(4, bean.getQuantità());
			preparedStmt.setBoolean(5, bean.isDisponibile());
			
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
	public void doUpdate(ProdottoFisicoBean bean)throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + ProdottoFisicoDS.TABLE_NAME
				+ "SET ID = ?, NOME = ?, PREZZO = ?, QUANTITà = ?, DISPONIBILE= ?, WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getNome());
			preparedStmt.setFloat(3, bean.getPrezzo());
			preparedStmt.setInt(4, bean.getQuantità());
			preparedStmt.setBoolean(5, bean.isDisponibile());
			
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
		
		String deleteSQL = "DELETE FROM " + ProdottoFisicoDS.TABLE_NAME
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
	public ProdottoFisicoBean doRetrieveByKey(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStmt = null;
		ProdottoFisicoBean bean = new ProdottoFisicoBean();
		
		String selectSQL = "SELECT * FROM " + ProdottoFisicoDS.TABLE_NAME
				+ "WHERE ID =  ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantità(rs.getInt("quantità"));
				bean.setDisponibilità(rs.getBoolean("disponibile"));
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
	public Collection<ProdottoFisicoBean> doRetrieveAll(String order) throws SQLException {
		
		Collection<ProdottoFisicoBean> prodotti_fisici = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + ProdottoFisicoDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ProdottoFisicoBean bean = new ProdottoFisicoBean();
				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantità(rs.getInt("quantità"));
				bean.setDisponibilità(rs.getBoolean("disponibile"));
				
				prodotti_fisici.add(bean);
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
		return prodotti_fisici;
	}
	
}