package it.unisa.unigame.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.unigame.model.bean.TicketBean;

public interface Ticket {
	
	public void doSave(TicketBean ticket) throws SQLException;
	
	public void doUpdate(TicketBean ticket) throws SQLException;
	
	public boolean doDelete(int num_ticket) throws SQLException; //remove by num ticket
	
	public TicketBean doRetrieveByKey(int num_ticket) throws SQLException;
	
	public Collection<TicketBean> doRetrieveAll(String orders) throws SQLException;
	

}
