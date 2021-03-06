package webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webapp.model.Domanda;
import webapp.model.Questionario;

public class QuestionarioDataMapper {
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");
	
	public boolean insert(Questionario questionario){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(questionario);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Aggiunto il questionario appena recuperato..");
		
		return true;
	}

	public Questionario findByID(int id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando il questionario con id : " + id);
		List<Questionario> questionario = entityManager.createQuery("from questionari where ID = :id", Questionario.class).setParameter("id", id).getResultList();
		System.out.println("Ho trovato il questionario");
		entityManager.getTransaction().commit();
		entityManager.close();
		if(questionario.isEmpty())
			return null;
		else
			return questionario.get(0);
	}
	
	public List<Questionario> findByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando il questionario");
		name = "%" + name + "%";
		List<Questionario> questionario = entityManager.createQuery("from questionari where Nome = :name", Questionario.class).setParameter("name", name).getResultList();
		System.out.println("Ho trovato il questionario");
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionario;
	}
	
	public List<Questionario> findByCategory(String categoria) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando il questionario");
		List<Questionario> questionario = entityManager.createQuery("from questionari where Categoria = :categoria", Questionario.class).setParameter("categoria", categoria).getResultList();
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
		entityManager.remove(entityManager.contains(toDeleteQuestionario) ? toDeleteQuestionario : entityManager.merge(toDeleteQuestionario));
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

	public List<Questionario> questionariUtente(String email){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Questionario> questionari = entityManager.createQuery("from questionari where Creatore = :email", Questionario.class).setParameter("email", email).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return questionari;
	}

	public void modificaQuestionario(int id, String nome, String categoria){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("modificando il questionario " + id + " con nome " + nome + "e categoria " + categoria);
		int ris = entityManager.createQuery("UPDATE questionari SET Nome = :nome, Categoria = :categoria WHERE id = :id").setParameter("nome", nome)
																											   .setParameter("categoria", categoria)
																											   .setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if(ris != 0)
			System.out.println(ris + " file aggiornati");
	}

}
