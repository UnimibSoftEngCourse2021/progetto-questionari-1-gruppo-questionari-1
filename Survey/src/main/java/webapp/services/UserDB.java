package webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import webapp.model.UtenteRegistrato;

@Repository
public class UserDB{

	@Autowired
	static JdbcTemplate jdbcTemplate;
	
    public static UtenteRegistrato find(String email){
    	// cercare utente nel database e ritornarne l'istanza
    	
    	String sql = "SELECT * FROM utente WHERE utente.Email = ' "+email+" ' ;";
    	List<UtenteRegistrato> utenti = jdbcTemplate.query(sql, new UserDataMapper());
    	UtenteRegistrato utente = utenti.get(0);
    	return utente;
    	
       // return new UtenteRegistrato("a", "b", "c", "d"); // ritorna n utente di esempio creato con dati presi casualmente
    }

    public static void insert(UtenteRegistrato u) {
    	String sql="INSERT INTO `utente` (`Email`, `Nome`, `Cognome`, `Password`) VALUES ('prova3@gmail', 'e', 'f', 'psw3');";
    	
        // inserisce all'interno del database l'utente u
    }

    public static boolean delete(String email) {

        // elimina dal databse l'utente con email passata per parametri

        return true;
    }
     
    public static boolean update(String email, String nome, String cognome, String password) {

        //aggiorna i dati di un utente con i dati passati per parametri

        return true;
    }

}

