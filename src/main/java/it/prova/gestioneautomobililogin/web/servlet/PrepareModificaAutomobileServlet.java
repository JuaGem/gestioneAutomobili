package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.service.MyServiceFactory;

@WebServlet("/PrepareModificaAutomobileServlet")
public class PrepareModificaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaAutomobileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String idParameter = request.getParameter("idAutomobile");

		try {

			request.setAttribute("edit_attribute",
					MyServiceFactory.getAutomobileServiceInstance().caricaSingoloElemento(Long.parseLong(idParameter)));
			RequestDispatcher rd = request.getRequestDispatcher("automobile/edit.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("automobile/search.jsp").forward(request, response);
			return;
		}

	}

}
