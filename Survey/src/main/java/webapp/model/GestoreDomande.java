package webapp.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import webapp.services.DomandaDataMapper;
import webapp.services.OpzioneDataMapper;
import webapp.services.UserDataMapper;

@Service
public class GestoreDomande {
	
	DomandaDataMapper domandaDataMapper = new DomandaDataMapper();
	OpzioneDataMapper opzioneDataMapper = new OpzioneDataMapper();
	UserDataMapper userDataMapper = new UserDataMapper();
	
	public Domanda creaDomanda(String testo,/* byte[] Immagine, */ String categoria, boolean domandaChiusa, UtenteRegistrato creatore, HashSet<Opzione> listaOpzioni) {
		System.out.println("Creazione di una domanda...");
		Domanda d = new Domanda(testo, /* Immagine,*/ categoria, domandaChiusa, creatore, listaOpzioni);
		Iterator<Opzione> i = listaOpzioni.iterator();
		while(i.hasNext())
		{
			Opzione o = i.next();
			o.setDomanda(d);
			System.out.println("Creazione di una opzione..."+ o.getDomanda().toString());
			//opzioneDataMapper.insert(o);
		}
		domandaDataMapper.insert(d);
		return d;
	}

	public Opzione creaOpzione(String descrizione) {
		Opzione o = new Opzione(descrizione);
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
