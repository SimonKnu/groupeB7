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
	public String goAdmin() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public String goInscription() {
		return "inscription.xhtml";
	}
}
