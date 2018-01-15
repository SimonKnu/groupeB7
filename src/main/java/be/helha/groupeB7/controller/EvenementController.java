package be.helha.groupeB7.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.sessionejb.GestionEvenementEJB;

@Named
@RequestScoped
public class EvenementController {

	@EJB
	private GestionEvenementEJB gestionEvenementEJB;
	
	public List<Evenement> doListEvent(){
		return gestionEvenementEJB.selectAll();
	}
}
