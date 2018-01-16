package be.helha.groupeB7.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.sessionejb.GestionPersonneEJB;

@Named
@RequestScoped
public class PersonneController {
/*
	@EJB
	private GestionPersonneEJB ejb;
*/	
	private String login, password, nom, prenom, mail;
	
	public String goAdmin() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public String goInscription() {
		return "inscription.xhtml";
	}
	
	public void createUtilisateur() {
		
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
}
