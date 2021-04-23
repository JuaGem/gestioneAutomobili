package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.model.Automobile;
import it.prova.gestioneautomobililogin.service.MyServiceFactory;
import it.prova.gestioneautomobililogin.utility.UtilityForm;

@WebServlet("/ExecuteModificaAutomobileServlet")
public class ExecuteModificaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String idInputParam = request.getParameter("idEditInput");
		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputParam = request.getParameter("cilindrata");
		String dataImmatricolazioneParam = request.getParameter("dataImmatricolazione");

		Date dataPubblicazioneParsed = UtilityForm.parseDateImmatricolazioneFromString(dataImmatricolazioneParam);

		if (!UtilityForm.validateInput(marcaInputParam, modelloInputParam, cilindrataInputParam,
				dataImmatricolazioneParam) || dataPubblicazioneParsed == null) {

			Automobile automobileTemp = new Automobile();
			automobileTemp.setId(Long.parseLong(idInputParam));
			automobileTemp.setMarca(marcaInputParam);
			automobileTemp.setModello(modelloInputParam);
			if (!cilindrataInputParam.isEmpty() && UtilityForm.checkInteger(cilindrataInputParam))
				automobileTemp.setCilindrata(Integer.parseInt(cilindrataInputParam));
			automobileTemp.setDataImmatricolazione(dataPubblicazioneParsed);

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("edit_attribute", automobileTemp);
			request.getRequestDispatcher("automobile/edit.jsp").forward(request, response);
			return;
		}

		Automobile automobileTemp = new Automobile(marcaInputParam, modelloInputParam,
				Integer.parseInt(cilindrataInputParam), dataPubblicazioneParsed);
		automobileTemp.setId(Long.parseLong(idInputParam));

		try {

			MyServiceFactory.getAutomobileServiceInstance().aggiorna(automobileTemp);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("automobile/edit.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("automobile/results.jsp").forward(request, response);

	}

}
