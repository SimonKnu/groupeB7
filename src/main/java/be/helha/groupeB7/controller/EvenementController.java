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
import be.helha.groupeB7.sessionejb.GestionEvenementEJB;
import be.helha.groupeB7.util.Tools;

@Named
@RequestScoped
public class EvenementController {
	private Evenement event;
	
	private String nom;
	private String lieu;
	private String description;
	private String dateDeb;
	private String dateFin;
	private Part file;


	@EJB
	private GestionEvenementEJB gestionEvenementEJB;
	
	public List<Evenement> doListEvent(){
		return gestionEvenementEJB.selectAll();
	}
	
	public String goDetailEvent(Evenement event) {
		this.event = event;
		return "detailEvenement.xhtml";
		
	}

	public void createEvent() {
		if(!nom.isEmpty() && !lieu.isEmpty() && !description.isEmpty() && !dateDeb.isEmpty() && !dateFin.isEmpty()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Evenement e;
			try {
				e = new Evenement(nom,lieu,description,formatter.parse(dateDeb),formatter.parse(dateFin), Tools.readImage(file.getInputStream()));
				gestionEvenementEJB.addEvenement(e);
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

	private void resetEvent() {
		this.nom="";
		this.lieu="";
		this.description="";
		this.dateDeb="";
		this.dateFin="";
	}
}
