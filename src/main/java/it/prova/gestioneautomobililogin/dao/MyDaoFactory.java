package it.prova.gestioneautomobililogin.dao;


public class MyDaoFactory {
	
	private static IUtenteDAO utenteDaoInstance = null;
	private static IAutomobileDAO automobileDaoInstance = null;

	public static IUtenteDAO getUtenteDAOInstance() {
		if (utenteDaoInstance == null)
			utenteDaoInstance = new UtenteDaoImpl();

		return utenteDaoInstance;
	}
	
	public static IAutomobileDAO getAutomobileDAOInstance() {
		if (automobileDaoInstance == null)
			automobileDaoInstance = new AutomobileDaoImpl();

		return automobileDaoInstance;
	}

}
