package be.helha.groupeB7.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.sessionejb.GestionEvenementEJB;
import be.helha.groupeB7.util.Tools;

@Named
@RequestScoped
public class EvenementController {
	private Evenement event;
	
	private int id;
	private String nom;
	private String lieu;
	private String description;
	private Date dateDeb;
	private Date dateFin;
	private Part file;


	@EJB
	private GestionEvenementEJB gestionEvenementEJB;
	
	public List<Evenement> doListEvent(){
		return gestionEvenementEJB.selectAll();
	}
	
	public List<Evenement> doListEventUser(Personne p){
		return gestionEvenementEJB.selectUserEvent(p);
	}
	
	public List<Evenement> doListEventConfirm(boolean confirm){
		return gestionEvenementEJB.selectConfirmEvent(confirm);
	}
	
	public String goDetailEvent(Evenement event) {
		this.event = event;
		return "detailEvenement.xhtml";	
	}
	
	public String goModificationEvent(Evenement event) {
		this.id = event.getId();
		this.nom = event.getNom();
		this.lieu = event.getLieu();
		this.description = event.getDescription();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dateDeb = formatter.parse(event.getDateDeb());
			this.dateFin = formatter.parse(event.getDateFin());
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "modificationEvent.xhtml";	
	}

	public String createEvent() {
		Evenement e;
		try {
			e = new Evenement(nom,lieu,description,dateDeb,dateFin, Tools.readImage(file.getInputStream()), true);
			gestionEvenementEJB.addEvenement(e);
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		resetEvent();

		return "index.xhtml?faces-redirect=true";
	}
	

	private void resetEvent() {
		this.nom="";
		this.lieu="";
		this.description="";
	}
	
	public void deleteEvent(Evenement event) {
		gestionEvenementEJB.deleteEvenement(event);
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

}
