package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.model.Automobile;
import it.prova.gestioneautomobililogin.service.IAutomobileService;
import it.prova.gestioneautomobililogin.service.MyServiceFactory;
import it.prova.gestioneautomobililogin.utility.UtilityForm;


@WebServlet("/ListAutomobiliServlet")
public class ListAutomobiliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}
		
		try {
		
			request.setAttribute("listaAutomobiliAttribute",MyServiceFactory.getAutomobileServiceInstance().listAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("automobile/search.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("automobile/results.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}
		
		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputParam = request.getParameter("cilindrata");
		String dataImmatricolazioneParam = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityForm.parseDateImmatricolazioneFromString(dataImmatricolazioneParam);

		IAutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();
		
		try {
		
				Automobile automobileInput = new Automobile(marcaInputParam, modelloInputParam,
						cilindrataInputParam != ""   ? Integer.parseInt(cilindrataInputParam) : null, dataImmatricolazioneParsed);
 
				request.setAttribute("listaAutomobiliAttribute",automobileService.cercaPerExample(automobileInput));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("automobile/search.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("automobile/results.jsp").forward(request, response);
	}

}
