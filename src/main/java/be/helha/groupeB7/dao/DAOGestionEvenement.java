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
	
	public void updateCouverture(int id, String filename) {
		
		Query q = em.createQuery("UPDATE e FROM Evenement e SET e.couverture = ? WHERE e.id =?");
		
		q.setParameter(1, this.readFile(filename));
		q.setParameter(2, id);
		
		q.executeUpdate();
		
	}
	
	
	// ENLEVER SI PAS UTILISE
	private byte[] readFile(String file) {
		
		ByteArrayOutputStream bos = null;
		
		File f = new File(file);
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for(int i; (i = fis.read(buffer)) != -1;)
				bos.write(buffer, 0, i);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos != null ? bos.toByteArray() : null;
		
	}
	
	public void deleteEvent(Evenement e) {
		
		em.remove(em.merge(e));
		
	}

}
