package be.helha.groupeB7.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evenement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String lieu;
	private String description;
	private Date dateDeb;
	private Date dateFin;
	private String imageURL;
	
	public Evenement() {
		
	}
	public Evenement(String nom, String lieu, String description, Date dateDeb, Date dateFin, String image) {
		this.nom = nom;
		this.lieu = lieu;
		this.description=description;
		this.dateDeb=dateDeb;
		this.dateFin=dateFin;
		this.imageURL=image;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
	
	@Override
	public String toString() {
		return "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", dateDeb="
				+ dateDeb + ", dateFin=" + dateFin + ", imageURL=" + imageURL;
	}
	
	
}
