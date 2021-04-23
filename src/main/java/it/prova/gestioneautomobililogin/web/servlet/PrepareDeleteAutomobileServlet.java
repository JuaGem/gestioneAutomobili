package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.service.MyServiceFactory;
import it.prova.gestioneautomobililogin.model.Automobile;

@WebServlet("/PrepareDeleteAutomobileServlet")
public class PrepareDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteAutomobileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("/login.jsp");
			return;
		}

		String idParameter = request.getParameter("idAutomobile");

		try {

			Automobile automobile = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idParameter));
			request.setAttribute("automobile_delete", automobile);
			request.getRequestDispatcher("/automobile/delete.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/search.jsp").forward(request, response);
			return;
		}

	}

}
