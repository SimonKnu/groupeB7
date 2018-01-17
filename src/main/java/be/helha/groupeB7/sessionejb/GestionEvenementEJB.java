package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.helha.groupeB7.dao.DAOGestionEvenement;
import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;

@Stateless
@LocalBean
public class GestionEvenementEJB implements IGestionEvenementEJBRemote{
	
	@EJB
	private DAOGestionEvenement dao;

	@Override
	public List<Evenement> selectAll() {
		// TODO Auto-generated method stub
		
		return dao.getEvents();
		
	}

	@Override
	public Evenement addEvenement(Evenement e) {
		// TODO Auto-generated method stub
		return dao.addEvent(e);
	}

	@Override
	public Evenement updateEvenement(Evenement e) {
		// TODO Auto-generated method stub
		return dao.updateEvent(e);
	}

	@Override
	public void deleteEvenement(Evenement e) {
		// TODO Auto-generated method stub
		dao.deleteEvent(e);
	}

	@Override
	public List<Evenement> selectUserEvent(Personne p) {
		// TODO Auto-generated method stub
		return dao.getUserEvent(u);
	}

}
