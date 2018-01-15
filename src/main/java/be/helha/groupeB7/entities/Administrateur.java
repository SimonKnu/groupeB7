package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Administrateur extends Personne implements Serializable{

	public Administrateur() {
		
	}
	
	public Administrateur(String log, String mdp) {
		super(log,mdp);
	}
	
	public String toString() {
		return "Admin "+super.toString();
	}
}
