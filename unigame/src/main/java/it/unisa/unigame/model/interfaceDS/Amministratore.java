package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.AmministratoreBean;

public interface Amministratore {
	public void doSave(AmministratoreBean amm) throws SQLException;
	
	public void doUpdate(AmministratoreBean amm) throws SQLException;
	
	public boolean doDelete(String username) throws SQLException; //remove by username
	
	public AmministratoreBean doRetrieveByKey(String username) throws SQLException;
	
	public AmministratoreBean doRetrieveByKeyEmail(String email) throws SQLException;
	
	public Collection<AmministratoreBean> doRetrieveAll(String order) throws SQLException;
	
	//public Collection<AmministratoreBean> doRetrieveAllExists(String order) throws SQLException;
}