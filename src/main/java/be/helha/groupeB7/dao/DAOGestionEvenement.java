package be.helha.groupeB7.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;
import be.helha.groupeB7.util.Tools;

@Stateless
@LocalBean
public class DAOGestionEvenement {
	
	@PersistenceContext(unitName = "groupeb7JTA")
	private EntityManager em;
	
	public List<Evenement> getEvents(){
		
		Query q = em.createQuery("SELECT e FROM Evenement e");
		
		return q.getResultList();
		
	}
	
	public List<Evenement> getUserEvent(Personne u){
		
		Query q = em.createQuery("SELECT p.events FROM Utilisateur p WHERE p.login = :login");
		q.setParameter("login", u.getLogin());
		
		return q.getResultList();
		
	}
	
	public List<Evenement> getConfirmEvent(boolean confirm){
		
		Query q = em.createQuery("SELECT e FROM Evenement e WHERE e.confirm = :confirm");
		
		if(confirm)
			q.setParameter("confirm", true);
		else
			q.setParameter("confirm", false);
		
		return q.getResultList();
		
	}
	
	public Evenement addEvent(Evenement e) {
		
		em.persist(e);
		return e;
		
	}
	
	public Evenement updateEvent(Evenement e) {
		
		em.merge(e);
		return e;
		
	}
	
	public void updateCouverture(int id, String filename) {
		
		Query q = em.createQuery("UPDATE e FROM Evenement e SET e.couverture = ? WHERE e.id =?");
		
		q.setParameter(1, Tools.readFile(filename));
		q.setParameter(2, id);
		
		q.executeUpdate();
		
	}
	
	
	public void deleteEvent(Evenement e) {
		
		em.remove(em.merge(e));
		
	}

}
