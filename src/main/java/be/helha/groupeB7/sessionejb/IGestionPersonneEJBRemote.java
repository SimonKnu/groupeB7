package be.helha.groupeB7.sessionejb;

import java.util.List;

import javax.ejb.Remote;

import be.helha.groupeB7.entities.Personne;

@Remote
public interface IGestionPersonneEJBRemote {
	
	public List<Personne> selectAll();
	public Personne addPersonne(Personne p);
	public Personne updatePersonne(Personne p);
	public void deletePersonne(Personne p);

}
