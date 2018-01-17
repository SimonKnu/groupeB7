package be.helha.groupeB7.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import be.helha.groupeB7.entities.Administrateur;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.sessionejb.GestionPersonneEJB;

@Named
@SessionScoped
public class ConnexionController implements Serializable {
	private Personne user;
	
	@EJB
	private GestionPersonneEJB ejb;
	
	public void savePersonne() {
		if(isConnect() == true) {
			if(user==null) {
				String name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
				user = ejb.getPersonne(name);
			}
		}
	}
	
	public boolean isConnect() {
		String name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		System.out.println(name);
		if(name != null) {
	    	return true;
	    }
	    return false;
	}

	
	
	
	public Personne getUser() {
		return user;
	}
	public void setUser(Personne user) {
		this.user = user;
	}
	
	

	
	
	
}
