package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.ComprendeOVBean;

public interface ComprendeOV {

public void doSave(ComprendeOVBean comprendeOV) throws SQLException;
	
	public void doUpdate(ComprendeOVBean comprendeOV) throws SQLException;
	
	public boolean doDelete(int id_videogioco, int id_ordine) throws SQLException; //remove by id videogame and id order
	
	public ComprendeOVBean doRetrieveByKey(int id_videogioco, int id_ordine) throws SQLException;
	
	public Collection<ComprendeOVBean> doRetrieveAll(String order) throws SQLException;
}
