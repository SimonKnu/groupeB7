package be.helha.groupeB7.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NavigationController {

	public String goAdmin() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public String goInscription() {
		return "inscription.xhtml?faces-redirect=true";
	}
	
	public String goCreer() {
		return "formEvent.xhtml?faces-redirect=true";
	}
	
	public String goAbout() {
		return "about.xhtml?faces-redirect=true";
	}
	
	public String goMyEvents() {
		return "myEvents.xhtml?faces-redirect=true";
	}
	
}
