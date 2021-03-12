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

    public Compilazione findByID(String Id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando la domanda");
		Compilazione questionario = entityManager.find(Compilazione.class,Id);
		System.out.println("Ho trovato la domanda");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario;
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

    public boolean remove(String Id) {
        Compilazione toDeleteCompilazione = this.findByID(Id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Rimuovendo una compilazione");
		entityManager.remove(toDeleteCompilazione);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Eliminata la compilazione");
		return true;
    }

}
