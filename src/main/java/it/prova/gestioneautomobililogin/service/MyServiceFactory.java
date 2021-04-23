package it.prova.gestioneautomobililogin.service;

import it.prova.gestioneautomobililogin.dao.*;

public class MyServiceFactory {
	
	private static IUtenteService UTENTE_SERVICE_INSTANCE = null;
	private static IUtenteDAO UTENTEDAO_INSTANCE = null;
	
	private static IAutomobileDAO AUTOMOBILEDAO_INSTANCE = null;
	private static IAutomobileService AUTOMOBILE_SERVICE_INSTANCE = null;

	public static IUtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTEDAO_INSTANCE == null)
			UTENTEDAO_INSTANCE = new UtenteDaoImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTEDAO_INSTANCE);

		return UTENTE_SERVICE_INSTANCE;
	}
	
	public static IAutomobileService getAutomobileServiceInstance() {
		if (AUTOMOBILE_SERVICE_INSTANCE == null)
			AUTOMOBILE_SERVICE_INSTANCE = new AutomobileServiceImpl();

		if (AUTOMOBILEDAO_INSTANCE == null)
			AUTOMOBILEDAO_INSTANCE = new AutomobileDaoImpl();

		AUTOMOBILE_SERVICE_INSTANCE.setAutomobileDAO(AUTOMOBILEDAO_INSTANCE);

		return AUTOMOBILE_SERVICE_INSTANCE;
	}
	
	
	

}
