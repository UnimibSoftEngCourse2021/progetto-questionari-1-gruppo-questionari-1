package webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="domande")
public class Domanda{
	
	@Id
	@GeneratedValue
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
	@JoinColumn(name="Creatore")
	private UtenteRegistrato creatore;
	
	//EAGER, carico tutte le opzioni della domanda, mappedBy domanda � il nome del campo nella classe Opzione
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "Domanda_ID")
	private Set<Opzione> opzioni = new HashSet<>();
	
	@ManyToMany
	private Set<Questionario> questionari = new HashSet<>();

	@OneToMany(mappedBy = "domandaId")
	private Set<CompilazioneDomanda> compilazioni = new HashSet<>();

	public Domanda(String testo, String immagine, String categoria, boolean domandaChiusa, UtenteRegistrato creatore, Set<Opzione> listaOpzioni) {
		this.testo = testo;
		this.immagine = immagine;
		this.categoria = categoria;
		this.domandaChiusa = domandaChiusa;
		this.creatore = creatore;
		this.opzioni = listaOpzioni;
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
	public Set<Questionario> getQuestionari(){
		return questionari;
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
