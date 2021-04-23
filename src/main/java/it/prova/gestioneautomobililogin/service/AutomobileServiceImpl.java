package it.prova.gestioneautomobililogin.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneautomobililogin.dao.IAutomobileDAO;
import it.prova.gestioneautomobililogin.model.Automobile;
import it.prova.gestioneautomobililogin.web.listener.LocalEntityManagerFactoryListener;

public class AutomobileServiceImpl implements IAutomobileService {
	
	private IAutomobileDAO automobileDAO;
	
	@Override
	public void setAutomobileDAO(IAutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;
	}

	@Override
	public List<Automobile> listAll() throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			automobileDAO.setEntityManager(entityManager);

			return automobileDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Automobile caricaSingoloElemento(Long id) throws Exception {
	
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			automobileDAO.setEntityManager(entityManager);

			return automobileDAO.findOne(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Automobile utenteInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			automobileDAO.setEntityManager(entityManager);
			
			automobileDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void inserisciNuovo(Automobile utenteInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
		
			entityManager.getTransaction().begin();
			
			automobileDAO.setEntityManager(entityManager);

			automobileDAO.insert(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void rimuovi(Automobile utenteInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			automobileDAO.setEntityManager(entityManager);
			
			automobileDAO.delete(utenteInstance);

			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}
	
	@Override
	public List<Automobile> cercaPerExample(Automobile example) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {

			automobileDAO.setEntityManager(entityManager);
			
			return automobileDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		
	}

}
