package be.helha.groupeB7.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.helha.groupeB7.entities.Evenement;
import be.helha.groupeB7.sessionejb.IGestionEvenementEJBRemote;
import be.helha.groupeB7.sessionejb.IGestionPersonneEJBRemote;

public class MainTestGlassfish {
	
	public static void main(String[] args) {
	
		IGestionEvenementEJBRemote bean = null;
	
		Context context = null;
		
		try {
			context = new InitialContext();
			bean = (IGestionEvenementEJBRemote) context.lookup("java:global/groupeB7/GestionEvenementEJB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
