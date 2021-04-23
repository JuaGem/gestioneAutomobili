package it.prova.gestioneautomobililogin.dao;

import it.prova.gestioneautomobililogin.model.Utente;

public interface IUtenteDAO extends IBaseDAO<Utente>{
	
	public Utente findAllByUserPassw(String user, String passw) throws Exception;

}
