package nl.zwolle.gameClasses;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Apples {

	
	private String naam;
	private int jaar;
	
	
	private int id;
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaam() {
		return naam;
	}


	public void setNaam(String naam) {
		this.naam = naam;
	}


	public int getJaar() {
		return jaar;
	}


	public void setJaar(int jaar) {
		this.jaar = jaar;
	}


	


	



}
