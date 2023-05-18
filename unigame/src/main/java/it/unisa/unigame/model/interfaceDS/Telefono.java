package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.TelefonoBean;

public interface Telefono {
	public void doSave(TelefonoBean tel) throws SQLException;
	
	public void doUpdate(TelefonoBean tel ) throws SQLException;
	
	public boolean doDelete(int num) throws SQLException; //remove by phone number
	
	public TelefonoBean doRetrieveByKey(int num) throws SQLException;
	
	public Collection<TelefonoBean> doRetrieveAll(String order) throws SQLException;
	
}