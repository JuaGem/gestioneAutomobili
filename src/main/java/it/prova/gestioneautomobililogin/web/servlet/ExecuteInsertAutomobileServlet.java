package it.prova.gestioneautomobililogin.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneautomobililogin.service.MyServiceFactory;
import it.prova.gestioneautomobililogin.model.Automobile;
import it.prova.gestioneautomobililogin.utility.UtilityForm;

@WebServlet("/ExecuteInsertAutomobileServlet")
public class ExecuteInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteInsertAutomobileServlet() {
        super();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputParam = request.getParameter("cilindrata");
		String dataImmatricolazioneParam = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityForm.parseDateImmatricolazioneFromString(dataImmatricolazioneParam);
		
		if (!UtilityForm.validateInput(marcaInputParam, modelloInputParam, cilindrataInputParam, dataImmatricolazioneParam)
			|| dataImmatricolazioneParam == null) {
			
			Automobile automobileInstance = new Automobile();
			automobileInstance.setMarca(marcaInputParam);
			automobileInstance.setModello(modelloInputParam);
			if(!cilindrataInputParam.isEmpty() && UtilityForm.checkInteger(cilindrataInputParam))
				automobileInstance.setCilindrata(Integer.parseInt(cilindrataInputParam));
			if(dataImmatricolazioneParsed!=null)
				automobileInstance.setDataImmatricolazione(dataImmatricolazioneParsed);
			
			request.setAttribute("automobileDaInserire", automobileInstance);
			
			request.setAttribute("errorMessage", "Attenzione, sono presenti errori di validazione");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}
		
		Automobile automobileInstance = new Automobile(marcaInputParam, modelloInputParam, Integer.parseInt(cilindrataInputParam), dataImmatricolazioneParsed);
		
		try {
			MyServiceFactory.getAutomobileServiceInstance().inserisciNuovo(automobileInstance);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si Ã¨ verificato un errore.");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/results.jsp").forward(request, response);
		
	}


}
