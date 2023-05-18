package it.unisa.unigame.control;

import java.io.IOException;
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String redirectPage;
		String ruolo = null;
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		
	}

}
