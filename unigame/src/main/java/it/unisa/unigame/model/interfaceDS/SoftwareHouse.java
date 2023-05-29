package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.SoftwareHouseBean;

public interface SoftwareHouse {
	public void doSave(SoftwareHouseBean soft) throws SQLException;
	
	public void doUpdate(SoftwareHouseBean soft) throws SQLException;
	
	public boolean doDelete(String nome) throws SQLException;
	
	public SoftwareHouseBean doRetrieveByKey(String nome) throws SQLException;
	
	public Collection<SoftwareHouseBean> doRetrieveAll(String order) throws SQLException;
	
}
