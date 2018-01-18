package be.helha.groupeB7.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import be.helha.groupeB7.entities.Administrateur;
import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.sessionejb.GestionPersonneEJB;
import be.helha.groupeB7.util.Tools;

@Named
@RequestScoped
public class PersonneController{

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
	private Date dateDeb;
	private Date dateFin;
	private Part file;
	
	//Email data
	private final String email = "geniessansfrontiere@gmail.com";
	private final String passwordEmail = "abc123def5";
	
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
		Evenement e;
		try {
			if(p instanceof Administrateur) {
				e = new Evenement(name,lieu,description,dateDeb,dateFin, Tools.readImage(file.getInputStream()), true,0);
			}
			else {
				e = new Evenement(name,lieu,description,dateDeb,dateFin, Tools.readImage(file.getInputStream()), false,0);
			}
			p.ajouterEvent(e);
			ejb.updatePersonne(p);
			this.resetEvent();
			
			if(p instanceof Administrateur) {
				sendMail();
				return "admin.xhtml?faces-redirect=true";
			}
			else {
				return "myEvents.xhtml?faces-redirect=true";
			}
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return "index.xhtml?faces-redirect=true";
	}
	
	public void deleteEventUser(Evenement event,Personne p) {
		p.supprimerEvent(event);
		ejb.updatePersonne(p);
	}
	
	public void deleteEvent(Evenement event) {
		Personne p = ejb.getPersonneFromEvent(event);
		p.supprimerEvent(event);
		ejb.updatePersonne(p);
		
	}

	
	private void resetEvent() {
		this.name="";
		this.lieu="";
		this.description="";
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
	
	
	
	
	
	
	public void sendMail() {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(email, passwordEmail);
				
			}
			
		});
		
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("geniessansfrontiere@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("geniessansfrontiere@gmail.com"));
			message.setSubject("MSF - Requête d'événement");
			message.setText("Un utilisateur a envoyé une requête afin d'accepter la publication de son événement");
			
			Transport.send(message);
			
			System.out.println("Done");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
