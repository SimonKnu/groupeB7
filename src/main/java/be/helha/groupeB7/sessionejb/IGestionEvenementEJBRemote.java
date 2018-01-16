package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.Remote;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.Utilisateur;

@Remote
public interface IGestionEvenementEJBRemote {
	
	public List<Evenement> selectAll();
	public List<Evenement> selectUserEvent(Utilisateur u);
	public Evenement addEvenement(Evenement e);
	public Evenement updateEvenement(Evenement e);
	public void deleteEvenement(Evenement e);

}
