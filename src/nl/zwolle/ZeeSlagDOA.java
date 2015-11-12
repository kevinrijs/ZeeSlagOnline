package nl.zwolle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.zwolle.gameClasses.Apples;
import nl.zwolle.gameClasses.Speler;



public class ZeeSlagDOA {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("apples2");
	
	
	public static Apples create(){

	

	Apples apple = new Apples();
	
	apple.setNaam("Sjaak");
	apple.setJaar(2000);

	
	
	EntityManager em = emf.createEntityManager();
	EntityTransaction t = em.getTransaction();
	t.begin();
	em.persist( apple );
	t.commit();
	em.close();
	
	return apple;
	}
	

	
	
	public static Speler saveSpeler(Speler player){
		
		
		
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( player );
		t.commit();
		em.close();
		
		return player;
	}

}
