package it.unisa.unigame.model.interfaceDS;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.unigame.model.bean.ClienteBean;

public interface Cliente {

	public void doSave(ClienteBean bean) throws SQLException;
	
	public void doUpdate(ClienteBean cliente, String cf, String nome, String cognome, String username, String email, String password, 
			String indirizzo_fatturazione, LocalDate data_di_nascita, boolean sospeso) throws SQLException;
	
	public boolean doDelete(String username) throws SQLException;
	
	public ClienteBean doRetrieveByKey(String username) throws SQLException;
	
	public ClienteBean doRetrieveByKeyEmail(String email) throws SQLException;
	
	public Collection<ClienteBean> doRetrieveAll(String order) throws SQLException;
}