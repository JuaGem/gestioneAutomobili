package it.prova.gestioneautomobililogin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneautomobililogin.model.Automobile;

public class AutomobileDaoImpl implements IAutomobileDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Utente",Automobile.class).getResultList();
	}
	
	@Override
	public Automobile findOne(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}
	
	@Override
	public void update(Automobile input) throws Exception {
		if (input == null) 
			throw new Exception("Problema valore in input");
			
		input = entityManager.merge(input);
	}
	
	@Override
	public void insert(Automobile input) throws Exception {
		if (input == null) 
			throw new Exception("Problema valore in input");

		entityManager.persist(input);
	}
	
	@Override
	public void delete(Automobile input) throws Exception {
		if (input == null) 
			throw new Exception("Problema valore in input");
		
		entityManager.remove(entityManager.merge(input));
	}
	
	@Override
	public List<Automobile> findByExample(Automobile example) throws Exception {
		if (example == null)
            throw new Exception("Valore di input non ammesso.");

        String query = "select a from Automobile a where 1=1 ";
        if (example.getMarca() != null && !example.getMarca().equals("")) {
            query += " and a.marca='" + example.getMarca() + "' ";
        }
        if (example.getModello() != null && !example.getModello().equals("")) {
            query += " and a.modello='" + example.getModello() + "' ";
        }

        if (example.getCilindrata() != null && example.getCilindrata() != 0) {
            query += " and a.cilindrata='" + example.getCilindrata() + "' ";
        }

        if (example.getDataImmatricolazione() != null && !example.getDataImmatricolazione().equals(null)) {
            java.sql.Date data = new java.sql.Date(example.getDataImmatricolazione().getTime());
            query += " and a.data_immatricolazione='" + data + "' ";
        }

        TypedQuery<Automobile> tquery = entityManager.createQuery(query, Automobile.class);
        return tquery.getResultList();
	}

}
