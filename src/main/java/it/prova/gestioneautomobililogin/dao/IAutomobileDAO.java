package it.prova.gestioneautomobililogin.dao;

import java.util.List;

import it.prova.gestioneautomobililogin.model.Automobile;

public interface IAutomobileDAO extends IBaseDAO<Automobile> {
	
	public List<Automobile> findByExample(Automobile example) throws Exception;

}
