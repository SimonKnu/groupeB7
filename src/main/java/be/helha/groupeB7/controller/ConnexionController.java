package be.helha.groupeB7.controller;

import java.io.Serializable;
import java.util.Date;

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
	private String login;
	private String password;
	private String mail;
	private String nom;
	private String prenom;
	private Date dateNais;

	
	@EJB
	private GestionPersonneEJB ejb;
	
	@EJB
	private GestionPersonneEJB gestionPersonneEJB;
	
	public void updatePersonne() {
		if (user instanceof Utilisateur) {
			user.setLogin(login);
			user.setPassword(password);
			user.setMail(mail);
			((Utilisateur) user).setNom(nom);
			((Utilisateur) user).setPrenom(prenom);
			((Utilisateur) user).setDateNais(dateNais);
			gestionPersonneEJB.updatePersonne(user);
		}else if (user instanceof Administrateur) {
			user.setLogin(login);
			user.setPassword(password);
			user.setMail(mail);
			gestionPersonneEJB.updatePersonne(user);
		}
	}
	
	/*
	 * M�thode qui permet de d�terminer si la personne est d�j� connect� ou non
	 * Si elle n'est pas connect�, on la sauvegarde dans un attribut user qui est toujours disponible 
	 * car on se trouve dans un Controller SessionScoped 
	 */
	public void savePersonne() {
		if(isConnect() == true) {
			//On doit s'assurer que l'"user" est null pour pas refaire la m�thode � chaque fois
			if(user==null) {
				//On r�cup�re le login de la personne gr�ce au FacesContext 
				String name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
				//Gr�ce au login r�cup�r�, on peut set l'user en faisant une requ�te 
				user = ejb.getPersonne(name);
				setVariable();
			}
		}
	}
	
	public void setVariable() {
		login = user.getLogin();
		password = user.getPassword();
		mail = user.getMail();
		
		if (user.getClass().equals(Utilisateur.class)) {
			Utilisateur user = (Utilisateur) this.user;
			nom = user.getNom();
			prenom = user.getPrenom();
			dateNais = user.getDateNais();
		}
	}
	
	
	/*
	 * M�thode qui permet de d�terminer si la personne est d�j� connect� ou non
	 */
	public boolean isConnect() {
		//S'il n'y a pas de session activ�, la m�thode renvoie null donc -> Pas connect�
		//S'il y en a une, la m�thode renvoie le login de l'user -> Connect� 
		String name = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if(name != null) {
	    	return true;
	    }
	    return false;
	}
	public boolean isBoolAdmin() {
		if(user!= null) {
			if(user instanceof Administrateur) {
				return true;
			}
		}
		return false;
	}
	public boolean isBoolUser() {
		if(user!= null) {
			if(user instanceof Utilisateur) {
				return true;
			}
		}
		return false;
	}
	

	
	public String goMonCompte() {
		if (user instanceof Utilisateur) {
			return "monCompteUser.xhtml?faces-redirect=true";
		}else if (user instanceof Administrateur) {
			return "monCompteAdmin.xhtml?faces-redirect=true";
		}
		return null;
	}

	
	
	public Personne getUser() {
		return user;
	}
	public void setUser(Personne user) {
		this.user = user;
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

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
}
