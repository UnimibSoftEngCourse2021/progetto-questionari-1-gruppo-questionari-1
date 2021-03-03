package webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Opzione")
public class Opzione {
	
	@Id
	@Column(name ="idOpzione")
	private int idOpzione;
	
	@Column(name ="descrizioneOpzione")
	private String descrizioneOpzione;
	
	@ManyToOne
	// @JoinColumn(name = "ID") non funziona
	private Domanda domanda;
	
	public Opzione() {
		
	}

	public String getDescrizioneOpzione() {
		return descrizioneOpzione;
	}

	public void setDescrizioneOpzione(String descrizioneOpzione) {
		this.descrizioneOpzione = descrizioneOpzione;
	}
}
