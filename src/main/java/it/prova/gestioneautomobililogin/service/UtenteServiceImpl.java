package it.prova.gestioneautomobililogin.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneautomobililogin.model.Utente;
import it.prova.gestioneautomobililogin.web.listener.LocalEntityManagerFactoryListener;
import it.prova.gestioneautomobililogin.dao.IUtenteDAO;

public class UtenteServiceImpl implements IUtenteService{
	
	private IUtenteDAO utenteDAO;

	@Override
	public void setUtenteDAO(IUtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public List<Utente> listAll() throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			utenteDAO.setEntityManager(entityManager);

			return utenteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente caricaSingoloElemento(Long id) throws Exception {
	
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			utenteDAO.setEntityManager(entityManager);

			return utenteDAO.findOne(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Utente utenteInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			utenteDAO.setEntityManager(entityManager);
			
			utenteDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
		
			entityManager.getTransaction().begin();

			
			utenteDAO.setEntityManager(entityManager);

			utenteDAO.insert(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void rimuovi(Utente utenteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			utenteDAO.setEntityManager(entityManager);
			
			utenteDAO.delete(utenteInstance);

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
	public Utente trovaPerUserPassw(String user, String passw)throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			utenteDAO.setEntityManager(entityManager);

			return utenteDAO.findAllByUserPassw(user, passw);
	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}


}
