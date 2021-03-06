package webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Opzione")
public class Opzione {
	
	@Id
	@GeneratedValue
	@Column(name ="idOpzione")
	private int idOpzione;
	
	@Column(name ="descrizioneOpzione")
	private String descrizioneOpzione;
	
	@ManyToOne
	@JoinColumn(name = "Domanda_ID", updatable = false, insertable = false)
	private Domanda domanda;
	
	public Opzione(String descrizione) {
		this.setDescrizioneOpzione(descrizione);
	}

	public String getDescrizioneOpzione() {
		return descrizioneOpzione;
	}

	public void setDescrizioneOpzione(String descrizioneOpzione) {
		this.descrizioneOpzione = descrizioneOpzione;
	}
	
	public void setDomanda(Domanda domanda) {
		this.domanda = domanda;
	}
}
