package webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Domanda")
@Table(name="Domanda")
public class Domanda {
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="Testo")
	private String testo;
	
	@Column(name="Immagine")
	private String immagine;
	
	@Column(name="Categoria")
	private String categoria;
	
	@Column(name="DomandaChiusa")
	private boolean domandaChiusa;
	
	@ManyToOne
	@Column(name="Creatore")
	private UtenteRegistrato creatore;
	
	//EAGER, carico tutte le opzioni della domanda, mappedBy domanda � il nome del campo nella classe Opzione
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "domanda")
	private Set<Opzione> opzioni = new HashSet<>();
	
	@ManyToMany
	private Set<Questionario> questionari = new HashSet<>();

	@OneToMany(mappedBy = "Domanda_ID")
	private Set<CompilazioneDomanda> compilazioni = new HashSet<CompilazioneDomanda>();

	public Domanda(String testo, String immagine, String categoria, boolean domandaChiusa, UtenteRegistrato creatore) {
		this.testo = testo;
		this.immagine = immagine;
		this.categoria = categoria;
		this.domandaChiusa = domandaChiusa;
		this.creatore = creatore;
	}

	public int getId() {
		return id;
	}

	public String getTesto() {
		return testo;
	}

	public String getImmagine() {
		return immagine;
	}

	public String getCategoria() {
		return categoria;
	}

	public boolean isDomandaChiusa() {
		return domandaChiusa;
	}

	public UtenteRegistrato getCreatore() {
		return creatore;
	}

	public Set<Opzione> getOpzioni() {
		return opzioni;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setDomandaChiusa(boolean domandaChiusa) {
		this.domandaChiusa = domandaChiusa;
	}

	public void setCreatore(UtenteRegistrato creatore) {
		this.creatore = creatore;
	}

	public void setOpzioni(Set<Opzione> opzioni) {
		this.opzioni = opzioni;
	}
	
	
}
