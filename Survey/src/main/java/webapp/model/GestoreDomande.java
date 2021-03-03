package webapp.model;

import org.springframework.stereotype.Service;

import webapp.services.DomandaDataMapper;

@Service
public class GestoreDomande {
	DomandaDataMapper domandaDataMapper = new DomandaDataMapper();

	public boolean creaDomanda(Domanda domanda) {
		// TODO Auto-generated method stub
		System.out.println("Creazione di una domanda...");
        return domandaDataMapper.insert(domanda);
	}

}