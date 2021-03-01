package webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import webapp.model.UtenteRegistrato;

@Repository
public class UserDB{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    public UtenteRegistrato find(String email){
    	// cercare utente nel database e ritornarne l'istanza
    	
    	String sql = "SELECT * FROM utente WHERE utente.Email = ' "+email+" ' ;";
    	List<UtenteRegistrato> utenti = jdbcTemplate.query(sql, new UserDataMapper());
    	UtenteRegistrato utente = utenti.get(0);
    	return utente;
    	
       // return new UtenteRegistrato("a", "b", "c", "d"); // ritorna n utente di esempio creato con dati presi casualmente
    }

    public boolean insert(UtenteRegistrato u) {
    	System.out.println("insert UserDB");
    	String sql="INSERT INTO `utente` (`Email`, `Nome`, `Cognome`, `Password`) VALUES ('"+u.getMail()+"', '"+u.getNome()+"', '"+u.getCognome()+"', '"+u.getPassword()+"');";
    	jdbcTemplate.update(sql);
    	return true;
        // inserisce all'interno del database l'utente u
    }

    public boolean delete(String email) {

        // elimina dal databse l'utente con email passata per parametri

        return true;
    }
     
    public boolean update(String email, String nome, String cognome, String password) {

        //aggiorna i dati di un utente con i dati passati per parametri

        return true;
    }

}

