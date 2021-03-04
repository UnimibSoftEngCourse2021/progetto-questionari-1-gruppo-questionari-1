package webapp.model;

import org.springframework.stereotype.Service;

import webapp.services.DomandaDataMapper;
import webapp.services.OpzioneDataMapper;

@Service
public class GestoreDomande {
	private Domanda domandaAttuale = null;
	
	DomandaDataMapper domandaDataMapper = new DomandaDataMapper();
	OpzioneDataMapper opzioneDataMapper = new OpzioneDataMapper();
	
	public boolean creaDomanda(Domanda domanda) {
		System.out.println("Creazione di una domanda...");
		domandaAttuale = domanda;
        return domandaDataMapper.insert(domanda);
	}

	public boolean creaOpzione(Opzione o) {
		System.out.println("Creazione di una opzione...");
		o.setDomanda(domandaAttuale);
		return opzioneDataMapper.insert(o);
	}

}
