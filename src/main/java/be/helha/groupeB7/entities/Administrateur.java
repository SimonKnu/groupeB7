package be.helha.groupeB7.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrateur extends Personne implements Serializable{

	public Administrateur() {
		
	}
	
	public Administrateur(String log, String mdp, String mail) {
		super(log,mdp,mail);
	}
	
	public String toString() {
		return "Admin "+super.toString();
	}
}
