package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.unigame.model.bean.FatturaBean;


public interface Fattura {
	public void doSave(FatturaBean fattura) throws SQLException;
	
	public void doUpdate(FatturaBean recensione,int id, int ordine, float importo_totale, LocalDateTime data_e_ora) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException; //remove by id
	
	public FatturaBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<FatturaBean> doRetrieveAll(String order) throws SQLException;
}
