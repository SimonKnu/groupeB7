package be.helha.groupeB7.controller;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.sessionejb.GestionPersonneEJB;

@Named
@RequestScoped
public class PersonneController {

	@EJB
	private GestionPersonneEJB ejb;
	
	private String login, password, nom, prenom, mail;
	private Date dateNaiss;
	
	public List<Personne> doListPersonne(){
		return ejb.selectAll();
	}
	
	public String goAdmin() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public String goInscription() {
		return "inscription.xhtml?faces-redirect=true";
	}
	
	public void createUtilisateur() {
		Utilisateur utilisateur = new Utilisateur(this.login, this.password, this.nom, this.prenom, this.dateNaiss, this.mail);
		ejb.addPersonne(utilisateur);
		resetUtilisateur();
	}
	
	public void resetUtilisateur() {
		this.login = "";
		this.password = "";
		this.nom = "";
		this.prenom = "";
		this.mail = "";
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
}
