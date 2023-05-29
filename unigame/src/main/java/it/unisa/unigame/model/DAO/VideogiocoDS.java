package it.unisa.unigame.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.unigame.model.bean.VideogiocoBean;
import it.unisa.unigame.model.bean.VideogiocoBean.Pegi;
import it.unisa.unigame.model.interfaceDS.Videogioco;

public class VideogiocoDS implements Videogioco{
	
static final String  TABLE_NAME= "videogioco";
	
	private DataSource ds=null;
	
	public VideogiocoDS(DataSource ds) {
		this.ds=ds;
	}
	

	@Override
	public void doSave(VideogiocoBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ VideogiocoDS.TABLE_NAME + "(ID,NOME, PREZZO, QUANTITà,PEGI,ANNO_PRODUZIONE, DISPONIBILE, PRODUTTORE)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getNome());
			preparedStmt.setFloat(3, bean.getPrezzo());
			preparedStmt.setInt(4, bean.getQuantità());
			preparedStmt.setString(5, bean.getPegi().name());
			preparedStmt.setInt(6, bean.getAnno_produzione());
			preparedStmt.setBoolean(7, bean.isDisponibile());
			preparedStmt.setString(7, bean.getProduttore());

			
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
	public void doUpdate(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + VideogiocoDS.TABLE_NAME
				+ "SET ID = ?, NOME = ?, PREZZO = ?, QUANTITà = ?,PEGI= ?,ANNO_PRODUZIONE= ?, DISPONIBILE= ?, PRODUTTORE= ?, WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, bean.getId());
			preparedStmt.setString(2, bean.getNome());
			preparedStmt.setFloat(3, bean.getPrezzo());
			preparedStmt.setInt(4, bean.getQuantità());
			preparedStmt.setString(5, bean.getPegi().name());
			preparedStmt.setInt(6, bean.getAnno_produzione());
			preparedStmt.setBoolean(7, bean.isDisponibile());
			preparedStmt.setString(8, bean.getProduttore());
			
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
		
		String deleteSQL = "DELETE FROM " + VideogiocoDS.TABLE_NAME
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
	public VideogiocoBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		VideogiocoBean bean = new VideogiocoBean();
		
		String selectSQL = "SELECT * FROM " + VideogiocoDS.TABLE_NAME
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
				bean.setPegi(Pegi.valueOf(rs.getString("pegi")));
				bean.setAnno_produzione(rs.getInt("anno_produzione"));
				bean.setDisponibilità(rs.getBoolean("disponibile"));
				bean.setProduttore(rs.getString("produttore"));
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
	public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException {

		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + VideogiocoDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				VideogiocoBean bean = new VideogiocoBean();
				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setQuantità(rs.getInt("quantità"));
				bean.setPegi(Pegi.valueOf(rs.getString("pegi")));
				bean.setAnno_produzione(rs.getInt("anno_produzione"));
				bean.setDisponibilità(rs.getBoolean("disponibile"));
				bean.setProduttore(rs.getString("produttore"));
				
				videogiochi.add(bean);
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
		return videogiochi;	
	}
}