package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.RecensioneBean;

public interface Recensione {

		public void doSave(RecensioneBean recensione) throws SQLException;
			
		public void doUpdate(RecensioneBean recensione) throws SQLException;
			
		public boolean doDelete(int id) throws SQLException; //remove by id
			
		public RecensioneBean doRetrieveByKey(int id) throws SQLException;
			
		public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException;
}
