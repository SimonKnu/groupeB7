package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.Remote;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Personne;
import be.helha.groupeB7.entities.Utilisateur;

@Remote
public interface IGestionEvenementEJBRemote {
	
	public List<Evenement> selectAll();
	public List<Evenement> selectTopEvents();
	public List<Evenement> selectUserEvent(Personne p);
	public List<Evenement> selectConfirmEvent(boolean confirm);
	public Evenement addEvenement(Evenement e);
	public Evenement updateEvenement(Evenement e);
	public void deleteEvenement(Evenement e);
}
