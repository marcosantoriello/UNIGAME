package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.VideogiocoBean;
import it.unisa.unigame.model.bean.VideogiocoBean.Pegi;

public interface Videogioco {

	public void doSave(VideogiocoBean videogame) throws SQLException;
		
		public void doUpdate(VideogiocoBean pf, int id, String nome, float prezzo, int quantità, Boolean disponbile, int anno_produzione, Pegi pegi, String produttore) throws SQLException;
		
		public boolean doDelete(int id) throws SQLException; //remove by id
		
		public VideogiocoBean doRetrieveByKey(int id) throws SQLException;
		
		public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException;
		
}
