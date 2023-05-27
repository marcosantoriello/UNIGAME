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

import it.unisa.unigame.model.bean.TicketBean;
import it.unisa.unigame.model.bean.TicketBean.Categoria;
import it.unisa.unigame.model.interfaceDS.Ticket;

public class TicketDS implements Ticket{
	
	static final String TABLE_NAME= "ticket";
	
	private DataSource ds=null;
	
	public TicketDS(DataSource ds) {
		this.ds=ds;
	}

	@Override
	public void doSave(TicketBean bean) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStmt=null;
		
		String insertSQL = "INSERT INTO "+ TicketDS.TABLE_NAME + "(NUM_TICKET, DATA_E_ORA, CLIENTE, GEST_ASS, CATEGORIA, MESSAGGIO, RISOLTO)"+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			
			preparedStmt.setInt(1, bean.getNum_ticket());
			preparedStmt.setTimestamp(2, Timestamp.valueOf(bean.getData_e_ora()));
			preparedStmt.setString(3, bean.getCliente());
			preparedStmt.setString(4, bean.getGestore());
			preparedStmt.setString(5, bean.getCategory().name());
			preparedStmt.setString(6, bean.getMessaggio());
			preparedStmt.setBoolean(7, bean.isRisolto());
			
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
	public void doUpdate(TicketBean ticket, int num, LocalDateTime data_e_ora, String cliente, String gestore,
			Categoria category, String mex, Boolean risolto) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQl = "UPDATE " + TicketDS.TABLE_NAME
				+ "SET NUM_TICKET= ?, DATA_E_ORA= ?, CLIENTE= ?, GEST_ASS= ?, CATEGORIA= ?, MESSAGGIO= ?, RISOLTO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQl);
			
			preparedStmt.setInt(1, num);
			preparedStmt.setTimestamp(2, Timestamp.valueOf(data_e_ora));
			preparedStmt.setString(3, cliente);
			preparedStmt.setString(4, gestore);
			preparedStmt.setString(5, category.name());
			preparedStmt.setString(6, mex);
			preparedStmt.setBoolean(7, risolto);
			
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
	public boolean doDelete(int num_ticket) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + TicketDS.TABLE_NAME
				+ "WHERE NUM_TICKET = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			
			preparedStmt.setInt(1, num_ticket);
			
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
	public TicketBean doRetrieveByKey(int num_ticket) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		TicketBean bean = new TicketBean();
		
		String selectSQL = "SELECT * FROM " + TicketDS.TABLE_NAME
				+ "WHERE NUM_TICKET =  ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, num_ticket);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				bean.setNum_ticket(rs.getInt("num_ticket"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setCliente(rs.getString("cliente"));
				bean.setGestore(rs.getString("gest_ass"));
				bean.setCategory(Categoria.valueOf(rs.getString("categoria")));
				bean.setMessaggio(rs.getString("messaggio"));
				bean.setRisolto(rs.getBoolean("risolto"));
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
	public Collection<TicketBean> doRetrieveAll(String order) throws SQLException {
		Collection<TicketBean> tickets = new LinkedList<>();
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String selectSQL = "SELECT * FROM " + TicketDS.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				TicketBean bean = new TicketBean();
				bean.setNum_ticket(rs.getInt("num_ticket"));
				bean.setData_e_ora(rs.getTimestamp("data_e_ora").toLocalDateTime());
				bean.setCliente(rs.getString("cliente"));
				bean.setGestore(rs.getString("gest_ass"));
				bean.setCategory(Categoria.valueOf(rs.getString("categoria")));
				bean.setMessaggio(rs.getString("messaggio"));
				bean.setRisolto(rs.getBoolean("risolto"));
				
				tickets.add(bean);
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
		return tickets;	
	}
	
}