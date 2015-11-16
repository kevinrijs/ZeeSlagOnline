package nl.zwolle.gameClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Boot {
	
	private int id2;
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId2() {
		return id2;
	}


	public void setId2(int id2) {
		this.id2 = id2;
	}

	public static void setBootNamen(String[] bootNamen) {
		Boot.bootNamen = bootNamen;
	}


	public static void setBootLengten(int[] bootLengten) {
		Boot.bootLengten = bootLengten;
	}


	public static void setIdCounter(int idCounter) {
		Boot.idCounter = idCounter;
	}


	public void setLigging(boolean ligging) {
		this.ligging = ligging;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setxMin(int xMin) {
		this.xMin = xMin;
	}


	public void setxMax(int xMax) {
		this.xMax = xMax;
	}


	public void setyMin(int yMin) {
		this.yMin = yMin;
	}


	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	public static final int VLIEGDEKSHIP = 0;
	public static final int SLAGSCHIP = 1;
	public static final int ONDERZEER = 2;
	public static final int TORPEDOJAGER = 3;
	public static final int PATROUILLESHIP = 4;

	public static String[] bootNamen = { "Vliegdekschip", "Slagschip", "Onderzeer", "Torpedojager", "Patrouilleship" };
	public static int[] bootLengten = {5, 4, 3, 3, 2};

	// static variabelen
	private static int idCounter = 1;

	// instance variables
	private int levens;
	private int lengte;
	private boolean dood;

	private boolean ligging; // true = horizontaal, false = verticaal
	private int id;

	// coordinaten van de gehele boot
	private int xMin;
	public static int getVliegdekship() {
		return VLIEGDEKSHIP;
	}

	public static int getSlagschip() {
		return SLAGSCHIP;
	}

	public static int getOnderzeer() {
		return ONDERZEER;
	}

	public static int getTorpedojager() {
		return TORPEDOJAGER;
	}

	public static int getPatrouilleship() {
		return PATROUILLESHIP;
	}

	public static String[] getBootNamen() {
		return bootNamen;
	}

	public static int[] getBootLengten() {
		return bootLengten;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public boolean isLigging() {
		return ligging;
	}

	public int getId() {
		return id;
	}

	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public int getyMax() {
		return yMax;
	}

	private int xMax;
	private int yMin;
	private int yMax;

	// Constructor die de lengte vanuit de Type Enum op de lengte van het boot
	// object set.
	public Boot(int bootType) {

		switch (bootType) {

		case 0:
			lengte = 5;
			break;
		case 1:
			lengte = 4;
			break;
		case 2:
			lengte = 3;
			break;
		case 3:
			lengte = 3;
			break;
		case 4:
			lengte = 2;
			break;
		}

		levens = lengte;
		this.id = idCounter;
		idCounter++;

	}

	// getters en setters

	public int getLengte() {
		return lengte;
	}

	public void setLengte(int lengte) {
		this.lengte = lengte;
	}

	public int getLevens() {
		return levens;
	}

	public void setLevens(int levens) {
		this.levens = levens;
	}

	public boolean isDood() {
		return dood;
	}

	public void setDood(boolean dood) {
		this.dood = dood;
	}

	// plaats boot
	public boolean plaatsBoot(Bord b, int x, int y, boolean ligging) {

		this.ligging = ligging;

		boolean legalePlaats = true;
		// als alle velden nog geen boten bevatten plaats de boot

		// bereken alle coordinaten
		xMin = x;
		yMin = y;

		if (ligging) {
			xMax = x + lengte - 1;
			yMax = y;

		} else {
			yMax = y + lengte - 1;
			xMax = x;
		}

		// bekijk of er al boten zijn op die posities
		// en of de boot niet buiten het veld valt.

		for (int ix = xMin; ix <= xMax; ix++) {
			for (int iy = yMin; iy <= yMax; iy++) {

				// als het vakje zelf of de vakjes om het gekozen vakje een boot
				// bevatten, of het is een ongeldig vakje kan de boot niet
				// geplaatst worden
				if ((!b.checkGeldigheidCoordinaten(ix, iy) ||b.giveVakje(ix,iy).isBevatBoot() || b.surroundedByBoats(ix, iy))) {

					legalePlaats = false;
					break;
				}
			}
		}
		// als dat niet zo is, plaats boot op elk vakje
		if (legalePlaats) {

			for (int ix = xMin; ix <= xMax; ix++) {
				for (int iy = yMin; iy <= yMax; iy++) {

					b.giveVakje(ix,iy).setBevatBoot(true, this);
					System.out.println("vakje gevuld: "+ ix +" "+ iy);

				}
			}
			
			return true;
		}
		return false;
	}

	public void verliesLeven() {
		levens--;
		if (levens <= 0) {
			dood = true;
		}
	}

}
