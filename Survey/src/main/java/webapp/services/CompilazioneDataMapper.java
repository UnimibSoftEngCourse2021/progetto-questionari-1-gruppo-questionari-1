package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import webapp.model.*;

public class CompilazioneDataMapper {
    
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

    public boolean insert(Compilazione compilazione, List<CompilazioneDomanda> listaRisposte){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(compilazione);
		for (CompilazioneDomanda lr : listaRisposte) {
			entityManager.persist(lr);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

    public Compilazione findByID(String id) {
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Compilazione compilazione = entityManager.find(Compilazione.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println(compilazione);
		return compilazione;
    }
    
    public List<Compilazione> findByCompilatore(UtenteRegistrato compilatore){
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando le domande");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compilazione> criteria = builder.createQuery(Compilazione.class);
		Root<Compilazione> from = criteria.from(Compilazione.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get("compilatore"), compilatore));
		TypedQuery<Compilazione> typed = entityManager.createQuery(criteria);
		    try {
		        	List<Compilazione> listaCompilazioni = typed.getResultList();
		        	return listaCompilazioni;
		    } catch (final NoResultException nre) {
		        return null;
		    }
    }

    public boolean remove(Compilazione c) {
        
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Rimuovendo una compilazione");
		entityManager.remove(entityManager.contains(c) ? c : entityManager.merge(c));
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Eliminata la compilazione");
		return true;
    }

}
