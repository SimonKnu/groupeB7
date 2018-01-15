package be.helha.groupeB7.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Utilisateur extends Personne implements Serializable{
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String log, String mdp, String nom, String prenom, Date date) {
		super(log,mdp,nom,prenom,date);
	}
	
	public String toString() {
		return "User "+super.toString();
	}
}
