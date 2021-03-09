package webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="Testo")
	private String testo;
	
	@Column(name="Immagine")
	private byte[] immagine;
	
	@Column(name="Categoria")
	private String categoria;
	
	@Column(name="DomandaChiusa")
	private boolean domandaChiusa;
	
	@ManyToOne
	@JoinColumn(name="Creatore")
	private UtenteRegistrato creatore;
	
	//EAGER, carico tutte le opzioni della domanda, mappedBy domanda � il nome del campo nella classe Opzione
	@OneToMany(mappedBy = "domanda", cascade=CascadeType.ALL)
	//@JoinColumn(name = "Domanda_ID")
	private Set<Opzione> opzioni = new HashSet<>();
	
	@ManyToMany
	private Set<Questionario> questionari = new HashSet<>();

	@OneToMany(mappedBy = "domandaId")
	private Set<CompilazioneDomanda> compilazioni = new HashSet<>();

	public Domanda(String testo,  byte[] immagine,  String categoria, boolean domandaChiusa, UtenteRegistrato creatore, HashSet<Opzione> listaOpzioni) {
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

	public void setId(int id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public byte[] getImmagine() {
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

	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean getDomandaChiusa() {
		return this.domandaChiusa;
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
