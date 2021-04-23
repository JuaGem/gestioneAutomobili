package it.prova.gestioneautomobililogin.service;

import java.util.List;

import it.prova.gestioneautomobililogin.dao.IAutomobileDAO;
import it.prova.gestioneautomobililogin.model.Automobile;

public interface IAutomobileService {
	
	public List<Automobile> listAll() throws Exception;

	public Automobile caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	public void setAutomobileDAO(IAutomobileDAO automobileDAO);
	
	public List<Automobile> cercaPerExample(Automobile example) throws Exception;

}
