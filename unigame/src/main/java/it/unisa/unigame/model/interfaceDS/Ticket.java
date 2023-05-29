package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.unigame.model.bean.TicketBean;
import it.unisa.unigame.model.bean.TicketBean.Categoria;

public interface Ticket {
	
	public void doSave(TicketBean ticket) throws SQLException;
	
	public void doUpdate(TicketBean ticket,int num, LocalDateTime data_e_ora, String cliente, String gestore, Categoria category, String mex, Boolean risolto) throws SQLException;
	
	public boolean doDelete(int num_ticket) throws SQLException; //remove by num ticket
	
	public TicketBean doRetrieveByKey(int num_ticket) throws SQLException;
	
	public Collection<TicketBean> doRetrieveAll(String orders) throws SQLException;
	

}
