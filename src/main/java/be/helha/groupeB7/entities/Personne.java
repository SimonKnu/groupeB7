package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Personne implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String login;
	private String password;
	private String mail;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Evenement> events = new ArrayList<Evenement>();
	
	
	public Personne() {
	}
	public Personne(String login, String password, String mail) {
		this.login = login;
		this.password = password;
		this.mail = mail;
	}

	
	
	public void ajouterEvent(Evenement event) {
		if(!events.contains(event)) {
			events.add(event);
		}
	}
	public void supprimerEvent(Evenement event) {
		events.remove(event);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	public String toString() {
		return id + " : "+login+" | "+password;
	}

}
