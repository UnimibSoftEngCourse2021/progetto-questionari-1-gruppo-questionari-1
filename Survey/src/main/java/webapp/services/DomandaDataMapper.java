package webapp.services;

import java.util.List;

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
	
	public List<Domanda> findByWord(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		word = "%" + word + "%";
		List<Domanda> domanda = entityManager.createQuery("from domanda where Testo = :word", Domanda.class).setParameter("word", word).getResultList();
		System.out.println("Ho trovato le domande");
		entityManager.getTransaction().commit();
		entityManager.close();
		return domanda;
	}
	
	public List<Domanda> findByCategory(String categoria) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		List<Domanda> domanda = entityManager.createQuery("from domanda where Testo = :categoria", Domanda.class).setParameter("categoria", categoria).getResultList();
		System.out.println("Ho trovato le domande");
		entityManager.getTransaction().commit();
		entityManager.close();
		return domanda;
	}

}
