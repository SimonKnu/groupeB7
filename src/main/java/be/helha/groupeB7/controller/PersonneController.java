package be.helha.groupeB7.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.sessionejb.GestionPersonneEJB;
import be.helha.groupeB7.util.Tools;

@Named
@RequestScoped
public class PersonneController {

	@EJB
	private GestionPersonneEJB ejb;
	
	//User data
	private String login, password, nom, prenom, mail;
	private Date dateNaiss;
	private List<Evenement> events = new ArrayList<Evenement>();
	
	//Event data
	private String name;
	private String lieu;
	private String description;
	private String dateDeb;
	private String dateFin;
	private Part file;
	
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
	
	public String createUserEvent(Personne p) {
		if(!name.isEmpty() && !lieu.isEmpty() && !description.isEmpty() && !dateDeb.isEmpty() && !dateFin.isEmpty()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Evenement e;
			Utilisateur u = (Utilisateur) p;
			try {
				e = new Evenement(name,lieu,description,formatter.parse(dateDeb),formatter.parse(dateFin), Tools.readImage(file.getInputStream()));
				u.ajouterEvent(e);
				ejb.updatePersonne(u);
				resetEvent();
			} 
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return "index.xhtml?faces-redirect=true";
	}
	
	private void resetEvent() {
		this.name="";
		this.lieu="";
		this.description="";
		this.dateDeb="";
		this.dateFin="";
	}
	
	public void addEvenementUser(Utilisateur u, Evenement e) {
		
		u.ajouterEvent(e);
		ejb.updatePersonne(u);
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	
	
}
