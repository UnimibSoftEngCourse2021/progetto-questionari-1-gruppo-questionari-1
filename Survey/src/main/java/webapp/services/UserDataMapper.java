package webapp.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import webapp.model.UtenteRegistrato;

public class UserDataMapper{

	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

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
		List<UtenteRegistrato> utenteRegistrato = entityManager.createQuery("from utente where Email = :email", UtenteRegistrato.class).setParameter("email", email).getResultList();
		System.out.println("Ho trovato l'utente");
		entityManager.getTransaction().commit();
		entityManager.close();
		return utenteRegistrato.get(0);
	}
}

