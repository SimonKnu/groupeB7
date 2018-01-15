package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.Remote;

import be.helha.groupeB7.entities.Evenement;

@Remote
public interface IGestionEvenementEJBRemote {
	
	public List<Evenement> selectAll();
	public Evenement addEvenement(Evenement e);
	public Evenement updateEvenement(Evenement e);
	public void deleteEvenement(Evenement e);

}
