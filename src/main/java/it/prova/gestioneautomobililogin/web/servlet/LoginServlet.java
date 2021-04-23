package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.model.Utente;
import it.prova.gestioneautomobililogin.service.MyServiceFactory;
import it.prova.gestioneautomobililogin.utility.UtilityForm;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userInputParam = request.getParameter("inputUsername");
		String passwInputParam = request.getParameter("inputPassword");
		
		
		
		if(!UtilityForm.validateInput(userInputParam, passwInputParam)) {
			
			Utente utenteLogin = new Utente(userInputParam,passwInputParam);
			
			request.setAttribute("UtenteLogin", utenteLogin);
			request.setAttribute("errorMessage", "Attenzione, sono presenti errori di validazione");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
			
		}
		
		try {
			
			Utente utenteLoggato = MyServiceFactory.getUtenteServiceInstance().trovaPerUserPassw(userInputParam, passwInputParam);
			
			request.getSession().setAttribute("userInfo", utenteLoggato);
			request.getRequestDispatcher("/search.jsp").forward(request, response);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Utente NON registrato!");
			response.sendRedirect("login.jsp");
			return;
		}
		
		
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

}
