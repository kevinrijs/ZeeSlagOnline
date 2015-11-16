package nl.zwolle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import nl.zwolle.gameClasses.Speler;




public class ZeeSlagDOA {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("apples");


	//	public static Apples create(){
	//
	//		Apples apple = new Apples();
	//
	//		apple.setNaam("Sjaak");
	//		apple.setJaar(2000);
	//
	//
	//
	//		EntityManager em = emf.createEntityManager();
	//		EntityTransaction t = em.getTransaction();
	//		t.begin();
	//		em.persist( apple );
	//		t.commit();
	//		em.close();
	//
	//		return apple;
	//	}


	public static void remove(int id){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Speler speler = em.find(Speler.class, id);
		if(speler != null){
			em.remove( speler );
		}
		t.commit();
		em.close();
	}

	/**
	 * Haal een rit op a.d.h.v. zijn id
	 */
	public static Speler find(int id){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Speler speler = em.find(Speler.class, id);
		t.commit();
		em.close();
		return speler;
	}

	/**
	 * Haal alle ritten op uit de database
	 */
	public static List<Speler> hosts(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Speler> spelers = em.createQuery("from Speler s where s.host is true and s.coupled is false", Speler.class).getResultList();
		t.commit();
		em.close();
		return spelers;
	}

	
	/**
	 * Haal alle ritten op uit de database
	 */
	public static List<Speler> all(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Speler> spelers = em.createQuery("from Speler", Speler.class).getResultList();
		t.commit();
		em.close();
		return spelers;
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

	public static Speler updateSpeler(Speler player){
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.merge(player);
		t.commit();
		em.close();

		return player;
	}



}
