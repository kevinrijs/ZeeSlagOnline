package nl.zwolle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.zwolle.gameClasses.Apples;
import nl.zwolle.gameClasses.Speler;




public class ZeeSlagDOA {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("apples");


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


	public static void remove(Long id){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Apples apple = em.find(Apples.class, id);
		if(apple != null){
			em.remove( apple );
		}
		t.commit();
		em.close();
	}

	/**
	 * Haal een rit op a.d.h.v. zijn id
	 */
	public static Apples find(Long id){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Apples apple = em.find(Apples.class, id);
		t.commit();
		em.close();
		return apple;
	}

	/**
	 * Haal alle ritten op uit de database
	 */
	public static List<Apples> all(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Apples> ritten = em.createQuery("from Apples", Apples.class).getResultList();
		t.commit();
		em.close();
		return ritten;
	}

		public static Speler saveSpeler(Speler player){
			System.out.println("hjhkkj");			
			EntityManager em = emf.createEntityManager();
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist( player );
			t.commit();
			em.close();
			
			return player;
		}

}
