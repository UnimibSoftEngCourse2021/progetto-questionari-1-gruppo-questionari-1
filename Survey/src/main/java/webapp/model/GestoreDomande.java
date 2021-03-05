package webapp.model;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import webapp.services.DomandaDataMapper;
import webapp.services.OpzioneDataMapper;
import webapp.services.UserDataMapper;

@Service
public class GestoreDomande {
	// private Domanda domandaAttuale = null;
	
	DomandaDataMapper domandaDataMapper = new DomandaDataMapper();
	OpzioneDataMapper opzioneDataMapper = new OpzioneDataMapper();
	UserDataMapper userDataMapper = new UserDataMapper();
	
	public boolean creaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore) {
		System.out.println("Creazione di una domanda...");
		UtenteRegistrato utente = userDataMapper.find(creatore);
		Domanda d = new Domanda(testo, Immagine, categoria, domandaChiusa, utente);
        return domandaDataMapper.insert(d);
	}

	public boolean creaOpzione(String descrizione, int IdDomanda) {
		System.out.println("Creazione di una opzione...");
		Domanda domanda = domandaDataMapper.findByID(IdDomanda);
		Opzione o = new Opzione(descrizione, domanda);
		return opzioneDataMapper.insert(o);
	}

	public boolean rimuoviDomanda(int ID) {
		return domandaDataMapper.remove(ID);
	}

	public List<Domanda> getDomandaByCategoria(String categoria) {
		return domandaDataMapper.findByCategory(categoria);
	}

}
