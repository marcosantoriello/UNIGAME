package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.ComprendeOPBean;

public interface ComprendeOP {

	public void doSave(ComprendeOPBean comprendeOP) throws SQLException;
	
	public void doUpdate(ComprendeOPBean comprendeOP) throws SQLException;
	
	public boolean doDelete(int id_prodotto, int id_ordine) throws SQLException; //remove by id product and id order
	
	public ComprendeOPBean doRetrieveByKey(int id_prodotto, int id_ordine) throws SQLException;
	
	public Collection<ComprendeOPBean> doRetrieveAll(String order) throws SQLException;
	
}
