package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.Domanda;
import webapp.model.Questionario;
import webapp.model.UtenteRegistrato;

public class QuestionarioDataMapper {
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");
	
	public boolean insert(Questionario questionario){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(questionario);
		entityManager.flush();
		entityManager.close();
		System.out.println("Aggiunto il questionario appena recuperato..");
		
		return true;
	}

	public Questionario findByID(int id){
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

	public boolean remove(int id){
		System.out.println("Recuperando il Questionario dal database..");
		Questionario toDeleteQuestionario = this.findByID(id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Rimuovendo un questionario");
		entityManager.remove(toDeleteQuestionario);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Eliminato il questionario appena recuperato..");
		return true;
	}

	public boolean addDomanda(int idQuestionario, Domanda domanda){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Questionario toUpdateQuestionario = this.findByID(idQuestionario);
		entityManager.getTransaction().begin();
		System.out.println("Sto aggiungendo una domanda");
		toUpdateQuestionario.getDomande().add(domanda);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Ho aggiunto una domanda");
		return true;
	}

	public boolean removeDomanda(int idQuestionario, Domanda domanda){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Questionario toUpdateQuestionario = this.findByID(idQuestionario);
		entityManager.getTransaction().begin();
		System.out.println("Sto eliminando una domanda");
		toUpdateQuestionario.getDomande().remove(domanda);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Ho eliminato una domanda");
		return true;
	}

	public List<Questionario> questionariUtene(UtenteRegistrato utente){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Questionario> questionari = entityManager.createQuery("from questionari where creatore := email", Questionario.class).setParameter("email", utente.getMail()).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionari;
	}

}
