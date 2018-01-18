package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.helha.groupeB7.dao.DAOGestionPersonne;
import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;

@Stateless
@LocalBean
public class GestionPersonneEJB implements IGestionPersonneEJBRemote {

	@EJB
	private DAOGestionPersonne dao;
	
	@Override
	public List<Personne> selectAll() {

		return dao.getPersonnes();
		
	}

	@Override
	public Personne addPersonne(Personne p) {
		// TODO Auto-generated method stub
	
		if(!dao.isDoublon(p.getLogin()))
			return dao.addPersonne(p);
		else
			return null;
		
	}

	@Override
	public Personne updatePersonne(Personne p) {
		// TODO Auto-generated method stub
		
		return dao.updatePersonne(p);
		
	}

	@Override
	public void deletePersonne(Personne p) {
		// TODO Auto-generated method stub
		
		dao.deletePersonne(p);
		
	}

	@Override
	public Personne getPersonne(String login) {
		// TODO Auto-generated method stub
		return dao.getPersonne(login);
	}
	
	@Override
	public Personne getPersonneFromEvent(Evenement e) {
		return dao.getPersonneFromEvent(e);
	}

}
