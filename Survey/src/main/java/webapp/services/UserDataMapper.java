package webapp.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


//import org.springframework.jdbc.core.RowMapper;

import webapp.model.UtenteRegistrato;

public class UserDataMapper{

	//implements RowMapper<UtenteRegistrato>

	public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.survey.jpa");

	public boolean insert(UtenteRegistrato utenteRegistrato){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(utenteRegistrato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public UtenteRegistrato find(String email){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Sto cercando l'utente");
		List<UtenteRegistrato> utenteRegistrato = entityManager.createQuery("from utente where Email = :email", UtenteRegistrato.class).setParameter("email", email).getResultList();
		System.out.println("Ho trovato l'utente");
		entityManager.getTransaction().commit();
		entityManager.close();
		return utenteRegistrato.get(0);
	}

	/*
	public UtenteRegistrato mapRow(ResultSet row, int rowNum) throws SQLException
	{
		UtenteRegistrato utente = new UtenteRegistrato();
		
		try
		{
			utente.setMail(row.getString("Email").trim());  
			utente.setNome(row.getString("Nome").trim());
			utente.setCognome(row.getString("Cognome").trim());
			utente.setPassword(row.getString("Password").trim());
		 }
		 catch (Exception ex)
		 {
			 System.out.println("Errore in ArticoliMapper.mapRow: " + ex);
		 }
		
 
		return utente;
	}
	*/
}

