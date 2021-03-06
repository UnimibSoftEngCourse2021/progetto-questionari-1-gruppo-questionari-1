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
	
	public Domanda creaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, String creatore, Set<Opzione> listaOpzioni) {
		System.out.println("Creazione di una domanda...");
		UtenteRegistrato utente = userDataMapper.find(creatore);
		Domanda d = new Domanda(testo, Immagine, categoria, domandaChiusa, utente, listaOpzioni);
		domandaDataMapper.insert(d);
		return d;
	}

	public Opzione creaOpzione(String descrizione) {
		System.out.println("Creazione di una opzione...");
		Opzione o = new Opzione(descrizione);
		opzioneDataMapper.insert(o);
		return o;
	}

	public boolean rimuoviDomanda(int ID) {
		return domandaDataMapper.remove(ID);
	}

	public List<Domanda> getDomandaByCategoria(String categoria) {
		return domandaDataMapper.findByCategory(categoria);
	}

	public Domanda getDomandaByID(int ID) {
		return domandaDataMapper.findByID(ID);
	}

}
