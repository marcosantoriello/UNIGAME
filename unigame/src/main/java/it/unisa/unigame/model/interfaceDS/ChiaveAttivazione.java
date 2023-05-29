package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.ChiaveAttivazioneBean;


public interface ChiaveAttivazione {

	public void doSave(ChiaveAttivazioneBean chiave_attivazione) throws SQLException;
	
	public void doUpdate(ChiaveAttivazioneBean recensione) throws SQLException;
		
	public boolean doDelete(long chiave) throws SQLException; //remove by id
		
	public ChiaveAttivazioneBean doRetrieveByKey(long chiave) throws SQLException;
		
	public Collection<ChiaveAttivazioneBean> doRetrieveAll(String order) throws SQLException;
	
}
