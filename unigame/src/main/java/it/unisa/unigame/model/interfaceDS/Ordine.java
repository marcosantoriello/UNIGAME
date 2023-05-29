package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.OrdineBean;

public interface Ordine {
	
	public void doSave(OrdineBean order) throws SQLException;
	
	public void doUpdate(OrdineBean order) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException; //remove by id
	
	public OrdineBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String orders) throws SQLException;
	
}
