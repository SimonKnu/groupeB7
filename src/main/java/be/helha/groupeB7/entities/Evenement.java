package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.mysql.jdbc.Blob;

@Entity
public class Evenement implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String lieu;
	@Lob
	private String description;
	private Date dateDeb;
	private Date dateFin;
	private boolean confirm;
	private int popularite;
	
	//Photo de couverture
	@Lob
	private byte[] couverture;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Image> images = new ArrayList<Image>();
	
	public Evenement() {
		
	}
	public Evenement(String nom, String lieu, String description, Date dateDeb, Date dateFin, byte[] couverture, boolean confirm, int popularite) {
		this.nom = nom;
		this.lieu = lieu;
		this.description=description;
		this.dateDeb=dateDeb;
		this.dateFin=dateFin;
		this.couverture = couverture;
		this.confirm = confirm;
		this.popularite = popularite;
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
	

	
	public String getDateDeb() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy");
		return formater.format(dateDeb);
	}
	public void setDateDeb(Date dateDeb) {
			this.dateDeb = dateDeb;
	}
	
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	public String getDateFin() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy");
		return formater.format(dateFin);
	}
	
	public Date getDateF() {
		return dateFin;
	}
	public Date getDateD() {
		return dateDeb;
	}
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	
	
	public void ajouterImageEvent(Image image) {
		if(!images.contains(image)) {
			images.add(image);
		}
	}
	
	public void supprimerImageEvent(Image image) {
		images.remove(image);
	}
	
	public int getPopularite() {
		return popularite;
	}
	public void setPopularite(int popularite) {
		this.popularite = popularite;
	}
	
	//Convertit le tableau de byte en base 64 et le renvoie sous forme de chaîne de caractère
	public String getCouverture() {
		byte[] tmp = Base64.getEncoder().encode(couverture);
		return new String(tmp);
	}
	public void setCouverture(byte[] couverture) {
		this.couverture = couverture;
	}
	
	
	
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", dateDeb="
				+ dateDeb + ", dateFin=" + dateFin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evenement other = (Evenement) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
