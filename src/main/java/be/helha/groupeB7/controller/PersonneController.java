package be.helha.groupeB7.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import be.helha.groupeB7.sessionejb.GestionPersonneEJB;

@Named
@RequestScoped
public class PersonneController {

	@EJB
	private GestionPersonneEJB ejb;
	
	
	
}