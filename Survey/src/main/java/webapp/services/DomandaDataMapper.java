package webapp.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.Domanda;

public class DomandaDataMapper {
	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

	public boolean insert(Domanda domanda){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(domanda);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

}
