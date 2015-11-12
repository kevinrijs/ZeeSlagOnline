package nl.zwolle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.zwolle.gameClasses.Speler;



public class ZeeSlagDOA {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ritten");
	
	public static Speler create(Speler player){
		
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( player );
		t.commit();
		em.close();
		
		return player;
	}

}
