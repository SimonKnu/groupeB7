package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Utilisateur extends Personne implements Serializable{
	private String nom;
	private String prenom;
	private Date dateNais;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Adresse domicile;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Evenement> events = new ArrayList<Evenement>();
	
	
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String log, String mdp, String nom, String prenom, Date date, String mail) {
		super(log,mdp,mail);
		this.nom=nom;
		this.prenom=prenom;
		this.dateNais=date;
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
	
	
	
	
	public void ajouterEvent(Evenement event) {
		if(!events.contains(event)) {
			events.add(event);
		}
	}
	public void supprimerEvent(Evenement event) {
		events.remove(event);
	}

	public String toString() {
		return "User "+super.toString();
	}
}
