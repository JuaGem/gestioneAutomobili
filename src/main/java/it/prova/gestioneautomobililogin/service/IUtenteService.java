package it.prova.gestioneautomobililogin.service;

import java.util.List;

import it.prova.gestioneautomobililogin.dao.IUtenteDAO;
import it.prova.gestioneautomobililogin.model.Utente;

public interface IUtenteService {
	
	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public Utente trovaPerUserPassw(String user, String passw)throws Exception;

	public void setUtenteDAO(IUtenteDAO utenteDAO);

}
