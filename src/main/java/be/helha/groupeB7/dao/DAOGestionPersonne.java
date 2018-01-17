package be.helha.groupeB7.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.groupeB7.entities.Personne;

@Stateless
@LocalBean
public class DAOGestionPersonne {
	
	@PersistenceContext(unitName = "groupeb7JTA")
	private EntityManager em;
	
	public List<Personne> getPersonnes(){
		
		Query q = em.createQuery("SELECT p FROM Personne p");
		
		return q.getResultList();
		
	}
	
	public Personne getPersonne(String login) {
		
		Query q = em.createQuery("SELECT p FROM Personne p WHERE p.login = :login");
		q.setParameter("login", login);
		
		List<Personne> personnes = q.getResultList();
		
		if(!personnes.isEmpty())
			return personnes.get(0);
		else
			return null;
		
	}
	
	public boolean isDoublon(String login) {
		
		Query q = em.createQuery("SELECT p FROM Personne p WHERE p.login = :login");
		q.setParameter("login", login);
		
		List<Personne> personnes = q.getResultList();
		
		if(personnes.size() == 0)
			return false;
		else
			return true;
		
	}
	
	public Personne addPersonne(Personne p) {
		
		if(!this.isDoublon(p.getLogin()))
			em.persist(p);
		
		return p;
		
	}
	
	public Personne updatePersonne(Personne p) {
		
		em.merge(p);
		return p;
		
	}
	
	public void deletePersonne(Personne p) {
		
		em.remove(em.merge(p));
		
	}

}
