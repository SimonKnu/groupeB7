package be.helha.groupeB7.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class NavigationController {

	public String goAdmin() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public String goInscription() {
		return "inscription.xhtml?faces-redirect=true";
	}
	
	public String goAbout() {
		return "about.xhtml?faces-redirect=true";
	}
	
	public String goMyEvents() {
		return "myEvents.xhtml?faces-redirect=true";
	}
	
	public String goList() {
		return "listEvent.xhtml?faces-redirect=true";
	}
	
	/*
	 * Méthode qui permet de se connecter, on doit simplement récupérer la session en cours et la "invalidate"
	 */
	public String deconnxion() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	    session.invalidate();
		return "index.xhtml?faces-redirect=true";
	}


}
