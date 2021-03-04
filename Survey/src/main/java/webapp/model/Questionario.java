package webapp.model;

import javax.persistence.*;
import java.util.*;


@Entity(name = "Questionario")
@Table(name = "Questionario")
public class Questionario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private String ID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @ManyToOne
    private UtenteRegistrato creatore;

    @OneToMany(mappedBy="Questionario_ID")
    private Set<Compilazione> compilazioni = new HashSet<Compilazione>();

    @ManyToMany
    @JoinTable(
        name = "domandaquestionario",
        joinColumns = @JoinColumn(name = "Questionario_ID"),
        inverseJoinColumns = @JoinColumn(name = "Domanda_ID")
    )
    private Set<Domanda> domande = new HashSet<>();

    //------------------> Costruttore

    public Questionario(String nome, String categoria, UtenteRegistrato creatore){
        this.setNome(nome);
        this.setCategoria(categoria);
        this.setCreatore(creatore);
    }

    //------------------> Getters & Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public UtenteRegistrato getCreatore() {
        return creatore;
    }

    public void setCreatore(UtenteRegistrato creatore) {
        this.creatore = creatore;
    }



}