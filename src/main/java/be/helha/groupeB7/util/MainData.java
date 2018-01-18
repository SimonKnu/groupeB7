package be.helha.groupeB7.util;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import be.helha.groupeB7.controller.EvenementController;
import be.helha.groupeB7.entities.Administrateur;
import be.helha.groupeB7.entities.Adresse;
import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.entities.UserGroup;
import be.helha.groupeB7.entities.Utilisateur;

public class MainData {

	public static void main(String[] args) {
			
		Utilisateur user1 = new Utilisateur("user1","mdpU-1","Knu","Simon",new Date(),"Mail1");
		Utilisateur user2 = new Utilisateur("user2","mdpU-2","Romain","Simon2",new Date(),"Mail2");
		Utilisateur user3 = new Utilisateur("user","helha","Altares","Valentin",new Date(),"Mail3");
		
		Administrateur admin = new Administrateur("admin","helha","geniessansfrontiere@gmail.com");
		
		Evenement event0 = new Evenement("Pluie","Soignies","PluiEDESCRIPTION",new Date(),new Date(), Tools.readFile("C:\\\\Users\\\\simon\\\\Pictures\\\\image3.jpg"), true,0);
		Evenement event1 = new Evenement("Soleil","Soignies","Descritiondusoleilàsoignies",new Date(),new Date(), Tools.readFile("C:\\\\Users\\\\simon\\\\Pictures\\\\image2.jpg"), true,0);
		Evenement event2 = new Evenement("Lune","Mars","Descriptionmarsàlune",new Date(),new Date(), Tools.readFile("C:\\\\Users\\\\simon\\\\Pictures\\\\image.jpg"), true,0);
		
		Adresse add1 = new Adresse("ad1", "1", 1111, "soignies");
		Adresse add2 = new Adresse("ad2", "2", 2222, "soignies");
		Adresse add3 = new Adresse("ad3", "3", 3333, "soignies");
		
		UserGroup userGroup1 = new UserGroup("user1", "user");
		UserGroup userGroup2 = new UserGroup("user2", "user");
		UserGroup userGroup3 = new UserGroup("user", "user");
		UserGroup adminGroup = new UserGroup("admin", "admin");
		
		user1.setDomicile(add1);
		user2.setDomicile(add2);
		user3.setDomicile(add3);
		
		user1.ajouterEvent(event2);
		user2.ajouterEvent(event1);
		admin.ajouterEvent(event0);
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeb7");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			em.persist(user1);
			em.persist(user2);
			em.persist(user3);
			em.persist(admin);
			
			em.persist(userGroup1);
			em.persist(userGroup2);
			em.persist(userGroup3);
			em.persist(adminGroup);
		tx.commit();
		em.clear();
		em.close();
		emf.close();
		
	}

}
