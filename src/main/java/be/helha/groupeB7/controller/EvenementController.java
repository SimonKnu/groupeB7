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
	private Evenement event;

	@EJB
	private GestionEvenementEJB gestionEvenementEJB;
	
	public List<Evenement> doListEvent(){
		return gestionEvenementEJB.selectAll();
	}
	
	public String goDetailEvent(Evenement event) {
		this.event = event;
		return "detailEvenement.xhtml";
	}

	public Evenement getEvent() {
		return event;
	}

	public void setEvent(Evenement event) {
		this.event = event;
	}
}
