package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.unigame.model.bean.RecensioneBean;
import it.unisa.unigame.model.bean.RecensioneBean.Indice_gradimento;

public interface Recensione {

		public void doSave(RecensioneBean recensione) throws SQLException;
			
		public void doUpdate(RecensioneBean recensione, int id, String cliente, int prodotto, int videogame, LocalDateTime data_e_ora,String descrizione, Indice_gradimento indice ) throws SQLException;
			
		public boolean doDelete(int id) throws SQLException; //remove by id
			
		public RecensioneBean doRetrieveByKey(int id) throws SQLException;
			
		public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException;
}
