package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.*;

public class CompilazioneDataMapper {
    
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

    public boolean insert(Compilazione compilazione){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(compilazione);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

    public Compilazione findByID(String Id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando la domanda");
		List<Compilazione> questionario = entityManager.createQuery("from compilazioni where ID = :id", Compilazione.class).setParameter("id", Id).getResultList();
		System.out.println("Ho trovato la domanda");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario.get(0);
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
