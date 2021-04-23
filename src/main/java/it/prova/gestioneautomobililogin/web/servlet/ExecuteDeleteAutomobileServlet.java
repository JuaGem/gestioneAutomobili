package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestioneautomobililogin.model.Automobile;
import it.prova.gestioneautomobililogin.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteAutomobileServlet")
public class ExecuteDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String idAutomobileParam = request.getParameter("idDeleteInput");

		if (!NumberUtils.isCreatable(idAutomobileParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("automobile/search.jsp").forward(request, response);
			return;
		}

		try {

			Automobile automobile = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idAutomobileParam));
			MyServiceFactory.getAutomobileServiceInstance().rimuovi(automobile);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("automobile/delete.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("automobile/results.jsp").forward(request, response);

	}

}
