package it.prova.gestioneautomobililogin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import it.prova.gestioneautomobililogin.model.Utente;

public class UtenteDaoImpl implements IUtenteDAO{
	
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente",Utente.class).getResultList();
	}

	@Override
	public Utente findOne(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) 
			throw new Exception("Problema valore in input");
		
		utenteInstance = entityManager.merge(utenteInstance);
	}

	@Override
	public void insert(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) 
			throw new Exception("Problema valore in input");
		

		entityManager.persist(utenteInstance);
	}

	@Override
	public void delete(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) 
			throw new Exception("Problema valore in input");
		
		entityManager.remove(entityManager.merge(utenteInstance));
	}

	@Override
	public Utente findAllByUserPassw(String user, String passw) throws Exception{
		if(user == null || passw == null)
			throw new Exception("Problema valore in input");
		
		TypedQuery<Utente> query = entityManager.createQuery("FROM Utente u where u.username=?1 and u.password=?2",Utente.class);
		query.setParameter(1, user);
		query.setParameter(2, passw);
		return query.getSingleResult();
	}

}
