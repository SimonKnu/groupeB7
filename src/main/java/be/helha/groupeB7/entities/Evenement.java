package be.helha.groupeB7.entities;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.mysql.jdbc.Blob;

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
	
	@Lob
	private byte[] couverture;
/*	
	@ManyToMany
	private List<Image> images = new ArrayList<Image>();;
	*/
	
	
	public Evenement() {
		
	}
	public Evenement(String nom, String lieu, String description, Date dateDeb, Date dateFin, byte[] couverture) {
		this.nom = nom;
		this.lieu = lieu;
		this.description=description;
		this.dateDeb=dateDeb;
		this.dateFin=dateFin;
		this.couverture = couverture;
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
		return this.dateDeb;
	}
	
/*	public String getDateDeb() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy");
		return formater.format(dateDeb);
	}*/
	
	public void setDateDeb(Date dateDeb) {

			this.dateDeb = dateDeb;

	}
	
	public Date getDateFin() {
		return this.dateFin;
	}
	
	/*public String getDateFin() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy");
		return formater.format(dateFin);
	}*/
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	
	/*
	public void ajouterImageEvent(Image image) {
		if(!images.contains(image)) {
			images.add(image);
		}
	}
	
	*/
	
	public String getCouverture() {
		byte[] tmp = Base64.getEncoder().encode(couverture);
		return new String(tmp);
	}
	public void setCouverture(byte[] couverture) {
		this.couverture = couverture;
	}
	
	
	
	
	@Override
	public String toString() {
		return "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", dateDeb="
				+ dateDeb + ", dateFin=" + dateFin;
	}
	
	
}
