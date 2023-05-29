package it.unisa.unigame.model.interfaceDS;

import java.sql.*;
import java.util.Collection;

import it.unisa.unigame.model.bean.GestoreAssistenzaBean;

public interface GestoreAssistenza {
	public void doSave(GestoreAssistenzaBean gest) throws SQLException;
	
	public void doUpdate(GestoreAssistenzaBean gest) throws SQLException;
	
	public boolean doDelete(String username) throws SQLException;
	
	public GestoreAssistenzaBean doRetrieveByKey(String username) throws SQLException;
	
	public GestoreAssistenzaBean doRetrieveByKeyEmail(String email) throws SQLException;
	
	public Collection<GestoreAssistenzaBean> doRetrieveAll(String order) throws SQLException;
}
