package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import webapp.model.Domanda;
import webapp.model.UtenteRegistrato;

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
	
	public List<Domanda> findByCategory(String categoria, UtenteRegistrato utente) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Domanda> criteria = builder.createQuery(Domanda.class);
		Root<Domanda> from = criteria.from(Domanda.class);
		Predicate byEmail = builder.equal(from.get("creatore"), utente);
		Predicate byCategory = builder.equal(from.get("categoria"), categoria);
		Predicate finalPredicate = builder.and(byEmail, byCategory);
		criteria.select(from);
		criteria.where(finalPredicate);
		TypedQuery<Domanda> typed = entityManager.createQuery(criteria);
		    try {
		        	List<Domanda> listaDomande = typed.getResultList();
		        	return listaDomande;
		    } catch (final NoResultException nre) {
		        return null;
		    }
	}
	
	public List<Domanda> findByUtente(String email) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Domanda> criteria = builder.createQuery(Domanda.class);
		Root<Domanda> from = criteria.from(Domanda.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("creatore"), email));
		TypedQuery<Domanda> typed = entityManager.createQuery(criteria);
		    try {
		        	List<Domanda> listaDomande = typed.getResultList();
		        	return listaDomande;
		    } catch (final NoResultException nre) {
		        return null;
		    }
	}

	public Domanda findByID(int id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando la domanda");
		Domanda domanda = entityManager.find(Domanda.class, id);
		System.out.println("Ho trovato la domanda");
		entityManager.getTransaction().commit();
		entityManager.close();
		return domanda;
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
