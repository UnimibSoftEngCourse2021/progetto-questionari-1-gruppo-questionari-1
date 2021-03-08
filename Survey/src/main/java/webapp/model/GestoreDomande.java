package webapp.model;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import webapp.services.DomandaDataMapper;
import webapp.services.OpzioneDataMapper;
import webapp.services.UserDataMapper;

@Service
public class GestoreDomande {
	//private Domanda domandaAttuale;
	
	DomandaDataMapper domandaDataMapper = new DomandaDataMapper();
	OpzioneDataMapper opzioneDataMapper = new OpzioneDataMapper();
	UserDataMapper userDataMapper = new UserDataMapper();
	
	public Domanda creaDomanda(String testo, String Immagine, String categoria, boolean domandaChiusa, UtenteRegistrato creatore, Set<Opzione> listaOpzioni) {
		System.out.println("Creazione di una domanda...");
		Domanda d = new Domanda(testo, Immagine, categoria, domandaChiusa, creatore, listaOpzioni);
		domandaDataMapper.insert(d);
		return d;
	}

	public Opzione creaOpzione(String descrizione) {
		System.out.println("Creazione di una opzione...");
		Opzione o = new Opzione(descrizione);
		// opzioneDataMapper.insert(o); dovrebbe farlo in automatico quando salvo la domanda
		return o;
	}

	public boolean rimuoviDomanda(int id) {
		return domandaDataMapper.remove(id);
	}

	public List<Domanda> getDomandaByCategoria(String categoria) {
		return domandaDataMapper.findByCategory(categoria);
	}

	public Domanda getDomandaByID(int id) {
		return domandaDataMapper.findByID(id);
	}

}
