package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.Questionario;

public class QuestionarioDataMapper {
	
	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");
	
	public boolean insert(Questionario questionario){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(questionario);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Aggiunto il questionario appena recuperato..");
		return true;
	}

	public Questionario findByID(String id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando il questionario");
		List<Questionario> questionario = entityManager.createQuery("from questionario where ID = :id", Questionario.class).setParameter("id", id).getResultList();
		System.out.println("Ho trovato il questionario");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario.get(0);
	}
	
	public List<Questionario> findByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando il questionario");
		name = "%" + name + "%";
		List<Questionario> questionario = entityManager.createQuery("from questionario where Nome = :name", Questionario.class).setParameter("name", name).getResultList();
		System.out.println("Ho trovato il questionario");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario;
	}
	
	public boolean remove(Questionario quesitoanrio) {
		
	}

	public boolean remove(String ID){
		System.out.println("Recuperando il Questionario dal database..");
		Questionario toDeleteQuestionario = this.findByID(ID)
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(toDeleteQuestionario);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Eliminato il questionario appena recuperato..");
		return true;
	}
}
