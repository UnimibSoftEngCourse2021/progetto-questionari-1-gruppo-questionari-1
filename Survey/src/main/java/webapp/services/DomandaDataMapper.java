package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.Domanda;

public class DomandaDataMapper {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

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
		List<Domanda> domande = entityManager.createQuery("from domande where Testo = :word", Domanda.class).setParameter("word", word).getResultList();
		System.out.println("Ho trovato le domande");
		entityManager.getTransaction().commit();
		entityManager.close();
		return domande;
	}
	
	public List<Domanda> findByCategory(String categoria) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		List<Domanda> domanda = entityManager.createQuery("from domande where Categoria = :categoria", Domanda.class).setParameter("categoria", categoria).getResultList();
		System.out.println("Ho trovato le domande");
		entityManager.getTransaction().commit();
		entityManager.close();
		return domanda;
	}

	public Domanda findByID(int id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando la domanda");
		List<Domanda> questionario = entityManager.createQuery("from domande where ID = :id", Domanda.class).setParameter("id", id).getResultList();
		System.out.println("Ho trovato la domanda");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario.get(0);
	}

	public boolean remove(int id){
		Domanda toDeleteDomanda = this.findByID(id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Rimuovendo una domanda");
		entityManager.remove(toDeleteDomanda);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Eliminata la domanda");
		return true;
	}

}
