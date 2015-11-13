package nl.zwolle.gameClasses;

import javax.persistence.Entity;


public class Bord {

	// bord instance variabelen
	private int bordBreedte;
	private int bordLengte;
	// getters and setters

	public int getBordBreedte() {
		return bordBreedte;
	}

	public void setBordBreedte(int bordBreedte) {
		this.bordBreedte = bordBreedte;
	}

	public int getBordLengte() {
		return bordLengte;
	}

	public void setBordLengte(int bordLengte) {
		this.bordLengte = bordLengte;
	}

	Vakje[][] vakjeArray;

	// bord constructor, maak bord dmv arraylist
	public Bord(int x, int y) {

		bordBreedte = x;
		bordLengte = y;
		vakjeArray = new Vakje[x][y];

		// Vul de Array met Vakjes.
		for (int ix = 0; ix < x; ix++) {

			for (int iy = 0; iy < y; iy++) {

				vakjeArray[ix][iy] = new Vakje();

			}
		}

	}

	// kijk of de coordinaat geldig is, dus binnen de het veld valt.
	public boolean checkGeldigheidCoordinaten(int x, int y) {
		if (x > bordBreedte - 1 || y > bordLengte - 1 || x < 0 || y < 0) {
			return false;
		}

		return true;

	}

	// Print het bord uit door op elk vakje in de vakjeArray een toString aan te
	// roepen
	public String toString(boolean eigenbord) {

		StringBuilder sb = new StringBuilder();

		for (int j = bordLengte - 1; j >= 0; j--) {

			for (int i = 0; i <= bordBreedte - 1; i++) {

				sb.append(vakjeArray[i][j].toString(eigenbord));
				sb.append(" "); // eerste is 0,9

			}
			sb.append("\n");
		}

		String output = sb.toString();

		return output;
	}

	public boolean surroundedByBoats(int x, int y) {

		boolean resultaat = false;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {

				if (checkGeldigheidCoordinaten(x+i, y+j) && !(i + j == 0) && !(i == j)) {
					if (vakjeArray[x+i][y+j].isBevatBoot()) {
						resultaat = true;
					}

				}
			}
		}

		return resultaat;
	}

}
