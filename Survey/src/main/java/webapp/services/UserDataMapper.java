package webapp.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.UtenteRegistrato;

public class UserDataMapper{

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

	public boolean insert(UtenteRegistrato utenteRegistrato){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(utenteRegistrato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public UtenteRegistrato find(String email){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando l'utente");
		UtenteRegistrato utente = entityManager.find(UtenteRegistrato.class, email);
		System.out.println("Ho trovato l'utente");
		entityManager.getTransaction().commit();
		entityManager.close();
		return utente;
	}
	
	public boolean remove(String email) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		UtenteRegistrato utente = find(email);
		entityManager.remove(utente);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}
	
	public boolean update(UtenteRegistrato utenteRegistrato) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		boolean checkRemove = remove(utenteRegistrato.getMail());
		boolean checkInsert = insert(utenteRegistrato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return (checkRemove && checkInsert);
	}
}

