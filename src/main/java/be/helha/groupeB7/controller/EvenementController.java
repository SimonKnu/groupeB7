package be.helha.groupeB7.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.derby.tools.sysinfo;

import be.helha.groupeB7.entities.Administrateur;
import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Image;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.sessionejb.GestionEvenementEJB;
import be.helha.groupeB7.util.Tools;

@Named
@SessionScoped
public class EvenementController implements Serializable {
	private Evenement event;
	
	private String nom;
	private String lieu;
	private String description;
	private Date dateDeb;
	private Date dateFin;
	private Part file;

	private Part fileImage;
	private String nomImage;
	private Evenement eventImage;

	@EJB
	private GestionEvenementEJB gestionEvenementEJB;
	
	//Renvoie une liste contenant les 4 événements les plus populaires
	public List<Evenement> doTopEvent(){
		return gestionEvenementEJB.selectTopEvents();
	}
	
	//Permet de confirmer un événement
	public void confirmer(Evenement evenement) {
		evenement.setConfirm(true);
		gestionEvenementEJB.updateEvenement(evenement);
	}
	
	//Renvoie la liste de tout les événements
	public List<Evenement> doListEvent(){
		return gestionEvenementEJB.selectAll();
	}
	
	//Renvoie la liste de tout les événements d'un tulisateur
	public List<Evenement> doListEventUser(Personne p){
		return gestionEvenementEJB.selectUserEvent(p);
	}
	
	//Renvoie la liste de tout les événements confirmés
	public List<Evenement> doListEventConfirm(boolean confirm){
		return gestionEvenementEJB.selectConfirmEvent(confirm);
	}
	
	
	public String goDetailEvent(Evenement event) {
		this.event = event; 
		this.event.setPopularite(this.event.getPopularite()+1); 
		gestionEvenementEJB.updateEvenement(this.event); 
		return "detailEvenement.xhtml";	
	}
	
	public String goDetailEventAdmin(Evenement event) {
		this.event = event;
		return "detailEvenement.xhtml";	
	}
	
	public String goModificationEvent(Evenement event) {
		this.event = event;
		this.nom = event.getNom();
		this.lieu = event.getLieu();
		this.description = event.getDescription();
		this.eventImage = event;
		this.dateDeb = event.getDateD();
		this.dateFin = event.getDateF();
	
		return "modificationEvent.xhtml?faces-redirect=true";	
	}
	
	public String modifEvent() {
		try {
			this.eventImage.setNom(nom);
			this.eventImage.setDescription(description);
			this.eventImage.setLieu(lieu);
			if(file!=null) {
				this.eventImage.setCouverture(Tools.readImage(file.getInputStream()));
			}			
			if(dateDeb!=null) {
				this.eventImage.setDateDeb(dateDeb);
			}
			if(dateFin!=null) {
				this.eventImage.setDateFin(dateFin);
			}
			gestionEvenementEJB.updateEvenement(eventImage);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return "modificationEvent.xhtml?faces-redirect=true";
	}

	
	public String ajouterImage(Personne p) {
		Image im;
		System.out.println(this.eventImage);
		try {
			im = new Image(nomImage,Tools.readImage(fileImage.getInputStream()));
			this.eventImage.ajouterImageEvent(im);
			gestionEvenementEJB.updateEvenement(getEventImage());
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		nomImage="";
		if(p instanceof Administrateur) {
			return "admin.xhtml?faces-redirect=true";
		}
		else {
			return "myEvents.xhtml?faces-redirect=true";
		}
	}
	
	public String supprimerImage(Image im) {
		
		this.eventImage.supprimerImageEvent(im);
		gestionEvenementEJB.updateEvenement(this.eventImage);
		
		return "modificationEvent.xhtml?faces-redirect=true";
		
	}
	
	
	public Evenement getEvent() {
		return event;
	}
	public void setEvent(Evenement event) {
		this.event = event;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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

	public Date getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}

	
	
	public Part getFileImage() {
		return fileImage;
	}
	public void setFileImage(Part fileImage) {
		this.fileImage = fileImage;
	}

	public String getNomImage() {
		return nomImage;
	}
	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public Evenement getEventImage() {
		return this.eventImage;
	}
	public void setEventImage(Evenement eventImage) {
		this.eventImage = eventImage;
	}
}
