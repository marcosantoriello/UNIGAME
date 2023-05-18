package it.unisa.unigame.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.unigame.model.interfaceDS.Amministratore;
import it.unisa.unigame.model.interfaceDS.Cliente;
import it.unisa.unigame.model.interfaceDS.GestoreAssistenza;
import it.unisa.unigame.model.DAO.AmministratoreDS;
import it.unisa.unigame.model.DAO.ClienteDS;
import it.unisa.unigame.model.DAO.GestoreAssistenzaDS;
import it.unisa.unigame.model.bean.AmministratoreBean;
import it.unisa.unigame.model.bean.ClienteBean;
import it.unisa.unigame.model.bean.GestoreAssistenzaBean;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String redirectPage;
		String ruolo = null;
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		try {
			ruolo = getRuolo(email, password);
			
			ClienteBean clienteBean = null;
			AmministratoreBean ammBean = null;
			GestoreAssistenzaBean gestAssBean = null;
			
			if (ruolo.equals("cliente")) {
				Cliente cliente = new ClienteDS(ds);
				clienteBean = cliente.doRetrieveByKeyEmail("email");
				request.getSession().setAttribute("utente", clienteBean);
				//request.getSession().setAttribute("carrello", new Carrello(ds));
			}
			else if (ruolo.equals("gestAssist")) {
				GestoreAssistenza gestAss = new GestoreAssistenzaDS(ds);
				gestAssBean = gestAss.doRetrieveByKeyEmail(email);
				request.getSession().setAttribute("utente", gestAssBean);
			}
			else if (ruolo.equals("admin")) {
				Amministratore admin = new AmministratoreDS(ds);
				ammBean = admin.doRetrieveByKeyEmail(email);
				request.getSession().setAttribute("utente", ammBean);
			}
			
			request.getSession().setAttribute("ruolo", ruolo);
			redirectPage = "/index.jsp";
		} catch (Exception e) {
			request.getSession().setAttribute("ruolo", null);
			redirectPage = "/index.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectPage);
	}
	
	private String getRuolo(String email, String password) throws LoginException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt1 = null;
		PreparedStatement preparedStmt2 = null;
		PreparedStatement preparedStmt3 = null;
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		String query1 = "SELECT PASS_WORD, RUOLO FROM CLIENTE"
				+ " WHERE EMAIL = ?";
		
		String query2 = "SELECT PASS_WORD, RUOLO FROM AMMINISTRATORE"
				+ " WHERE EMAIL = ?";
		
		String query3 = "SELECT PASS_WORD, RUOLO FROM GESTORE_ASSISTENZA"
				+ " WHERE EMAIL = ?";
		
		String ruolo1 = null;
		String ruolo2 = null;
		String ruolo3 = null;
		
		try {
			//CHECK CLIENTE
			preparedStmt1 = connection.prepareStatement(query1);
			preparedStmt1.setString(1, email);
			ResultSet rs1 = preparedStmt1.executeQuery();
			String password1 = null;
			if (rs1.next()) {
				password1 = rs1.getString("pass_word");
				if (password1.equals(password))
					ruolo1 = rs1.getString("ruolo");
			}
			
			//CHECK ADMIN
			preparedStmt2 = connection.prepareStatement(query2);
			preparedStmt2.setString(1, email);
			ResultSet rs2 = preparedStmt2.executeQuery();
			String password2 = null;
			if (rs2.next()) {
				password2 = rs2.getString("pass_word");
				if (password1.equals(password))
					ruolo2 = rs2.getString("ruolo");
			}
			
			//CHECK CLIENTE
			preparedStmt3 = connection.prepareStatement(query3);
			preparedStmt3.setString(1, email);
			ResultSet rs3 = preparedStmt1.executeQuery();
			String password3 = null;
			if (rs3.next()) {
				password3 = rs3.getString("pass_word");
				if (password3.equals(password))
					ruolo3 = rs3.getString("ruolo");
			}
			
			connection.setAutoCommit(false);
			connection.commit();
		} finally  {
			try {
				if (preparedStmt1 != null)
					preparedStmt1.close();
				if (preparedStmt2 != null)
					preparedStmt2.close();
				if (preparedStmt3 != null)
					preparedStmt3.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
			
		if (ruolo1 != null) {
			return ruolo1;
		}
		else if (ruolo2 != null) {
			return ruolo2;
		}
		else if (ruolo3 != null) {
			return ruolo3;
		}
		else {
			throw new LoginException("Invalid email or password!");
		}
	}

}
