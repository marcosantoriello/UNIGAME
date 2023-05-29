package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.ProdottoFisicoBean;

public interface ProdottoFisico {

public void doSave(ProdottoFisicoBean pf) throws SQLException;
	
	public void doUpdate(ProdottoFisicoBean pf) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException; //remove by id
	
	public ProdottoFisicoBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<ProdottoFisicoBean> doRetrieveAll(String order) throws SQLException;
	
}
