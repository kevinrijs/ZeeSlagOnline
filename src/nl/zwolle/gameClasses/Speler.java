package nl.zwolle.gameClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Speler {

	public int getHoeveelheidBoten() {
		return hoeveelheidBoten;
	}

	public void setHoeveelheidBoten(int hoeveelheidBoten) {
		this.hoeveelheidBoten = hoeveelheidBoten;
	}

	protected String naam;
	protected Bord bord;
	protected List<Boot> bootArray= new ArrayList<Boot>();
	protected int hoeveelheidBoten = 0;
	protected boolean  host = false;
	protected boolean  coupled = false;
	protected boolean  hisTurn = false;
	protected int opponentId = -1;
	

	

	public boolean isHisTurn() {
		return hisTurn;
	}

	public void setHisTurn(boolean hisTurn) {
		this.hisTurn = hisTurn;
	}

	public int getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(int opponentId) {
		this.opponentId = opponentId;
	}

	public boolean isCoupled() {
		return coupled;
	}

	public void setCoupled(boolean coupled) {
		this.coupled = coupled;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	// Overloaded constructors met standaard waarden: Naam=AI, x=10, y=10
	// (grootte van het bord)
	public Speler() {
		this("AI");

	}

	public Speler(String naam) {
		this(naam, 10, 10);

	}

	public Speler(String naam, int xCoordinaat, int yCoordinaat) {
		this.bord = new Bord(xCoordinaat, yCoordinaat);
		this.naam = naam;
	}
	private int id;
	@Id
	//@Column(name="SPELER_ID")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	//getters setters
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="SPELER_ID")
	public List<Boot> getBootArray() {
		return bootArray;
	}

	public void setBootArray(List<Boot> bootArray) {
		this.bootArray = bootArray;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@OneToOne(cascade = {CascadeType.ALL})  //fetch=FetchType.LAZY
	//@JoinColumn(name="SPELER_ID")
	public Bord getBord() {
		return bord;
	}

	public void setBord(Bord bord) {
		this.bord = bord;
	}

	// als coordinaat geldig is en niet al eerder is beschoten, schiet
	public boolean schietOpVakje(Bord bord, int x, int y) {

		// user ingegeven coordinaten worden -1 geshift zodat ze in de array
		// passen.
		// int x = xCoordinaat-1;
		// int y = yCoordinaat-1;

		if (bord.checkGeldigheidCoordinaten(x, y) && !(bord.giveVakje(x,y).isBeschoten())) {

			bord.giveVakje(x,y).setBeschoten(true);

			if (bord.giveVakje(x,y).isBevatBoot()) {
				System.out.println("Boem!");
				
				bord.giveVakje(x,y).boot.verliesLeven();
				if (bord.giveVakje(x,y).boot.isDood()) {
					System.out.println("Boot gezonken");
				}
				return true;

			} else {
				System.out.println("Plons");
				return true;
			}

		}
		return false;

	}

	// Maakt nieuwe boten aan die op het bord geplaatst worden, de plaatsBoot
	// geeft een boolean terug die aangeeft of de aangegeven locatie geldig is.
	public boolean nieuweBoot(int x, int y, boolean ligging, int bootID) {

		Boot nieuwBoot = new Boot(bootID);

		if (nieuwBoot.plaatsBoot(bord, x, y, ligging)) {

			bootArray.add(nieuwBoot);
			hoeveelheidBoten++;
			
			return true;
		}
		
		return false;
	}

	// controleerd of de speler heeft verloren door de hoeveelheid boten in
	// totaal te vergelijken met de hoeveelheid dode boten. als deze gelijk zijn
	// geeft de methode true terug
	public boolean spelerHeeftVerloren() {

		int teller = 0;
		for (Boot boot : bootArray) {
			if (boot.isDood()) {
				teller++;
			}
		}
		if (teller == hoeveelheidBoten) {
			return true;
		}
		return false;
		
	}

}
