package webapp.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import webapp.model.UtenteRegistrato;

public class UserDataMapper implements RowMapper<UtenteRegistrato> {
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

}

