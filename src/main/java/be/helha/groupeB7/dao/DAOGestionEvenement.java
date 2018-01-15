package be.helha.groupeB7.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.groupeB7.entities.Evenement;

@Stateless
@LocalBean
public class DAOGestionEvenement {
	
	@PersistenceContext(unitName = "groupeb7JTA")
	private EntityManager em;
	
	public List<Evenement> getEvents(){
		
		Query q = em.createQuery("SELECT e FROM Evenement e");
		
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
	
	public void deleteEvent(Evenement e) {
		
		em.remove(em.merge(e));
		
	}

}
