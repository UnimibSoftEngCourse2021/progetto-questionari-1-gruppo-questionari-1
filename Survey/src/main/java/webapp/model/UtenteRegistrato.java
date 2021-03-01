package webapp.model;

import java.io.Serializable;

public class UtenteRegistrato implements Serializable{
    
	private static final long serialVersionUID = 8097216674312855410L;
	
	private String nome;
    private String cognome;
    private String mail;
    private String password;

// --------------------> costruttore

    public UtenteRegistrato() {
    }
    
    public UtenteRegistrato(String nome, String cognome, String mail, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setMail(mail);
        this.setPassword(password);
    }

// --------------------> get e set degli attributi

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
        if(nome != null){
            this.nome = nome;
        }else{
            System.out.println("~valore nullo");
        }
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
        if(cognome != null){
            this.cognome = cognome;
        }else{
            System.out.println("~valore nullo");
        }
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		if(mail != null){
            this.mail = mail;
        }else{
            System.out.println("~valore nullo");
        }
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		if(password != null){
            this.password = password;
        }else{
            System.out.println("~valore nullo");
        }
	}


}