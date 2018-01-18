package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

@Entity
public class Image implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String imageName;
	
	@Lob
	private byte[] image;
	
	
	public Image() {
		
	}
	public Image(String nom, byte[] image) {
		this.imageName=nom;
		this.image=image;
	}

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImage() {
		byte[] tmp = Base64.getEncoder().encode(image);
		return new String(tmp);
	}
	public void setImage(byte[] img) {
		this.image = img;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
