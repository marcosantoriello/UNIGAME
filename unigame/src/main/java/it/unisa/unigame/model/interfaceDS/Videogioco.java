package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.VideogiocoBean;

public interface Videogioco {

	public void doSave(VideogiocoBean videogame) throws SQLException;
		
		public void doUpdate(VideogiocoBean pf) throws SQLException;
		
		public boolean doDelete(int id) throws SQLException; //remove by id
		
		public VideogiocoBean doRetrieveByKey(int id) throws SQLException;
		
		public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException;
		
}
