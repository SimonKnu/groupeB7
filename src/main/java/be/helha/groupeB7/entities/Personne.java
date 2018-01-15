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
	private String nom;
	private String prenom;
	private Date dateNais;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Adresse domicile;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Evenement> events = new ArrayList<Evenement>();
	
	
	
	public Personne() {
	}
	public Personne(String login, String password, String nom, String prenom, Date date) {
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNais = date;
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
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Date getDateNais() {
		return dateNais;
	}
	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}
	
	public Adresse getDomicile() {
		return domicile;
	}
	public void setDomicile(Adresse domicile) {
		this.domicile = domicile;
	}
	
	public String toString() {
		return id + " : "+login+" | "+password+" | "+nom+" | "+prenom+" | "+dateNais;
	}

}
