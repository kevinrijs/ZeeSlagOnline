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

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Bord {
	
	private int id;
	@Id
	//@Column(name="BORD_ID")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	// bord instance variabelen
	private int bordBreedte;
	private int bordLengte;
	
	List<Vakje> vakjeArray = new ArrayList<Vakje>();
	// getters and setters

	public int getBordBreedte() {
		return bordBreedte;
	}

	public void setBordBreedte(int bordBreedte) {
		this.bordBreedte = bordBreedte;
	}

	@OneToMany(cascade = {CascadeType.ALL}) // fetch=FetchType.LAZY
	//@JoinColumn(name="BORD_ID")
	public List<Vakje> getVakjeArray() {
		return vakjeArray;
	}

	public void setVakjeArray(List<Vakje> vakjeArray) {
		this.vakjeArray = vakjeArray;
	}

	public int getBordLengte() {
		return bordLengte;
	}

	public void setBordLengte(int bordLengte) {
		this.bordLengte = bordLengte;
	}

	

	// bord constructor, maak bord dmv arraylist
	public Bord(){
		
	}
	
	public Bord(int x, int y) {

		this.bordBreedte = x;
		this.bordLengte = y;
		

		// Vul de Array met Vakjes.
		for (int i = 0; i < x*y; i++) {
			vakjeArray.add(new Vakje());
		}

	}

	// kijk of de coordinaat geldig is, dus binnen de het veld valt.
	public boolean checkGeldigheidCoordinaten(int x, int y) {
		if (x > bordBreedte - 1 || y > bordLengte - 1 || x < 0 || y < 0) {
			return false;
		}

		return true;

	}
	
	public Vakje giveVakje(int x, int y){
		
		int vakje = x*bordLengte+y;
		return vakjeArray.get(vakje);
		
	}

	// Print het bord uit door op elk vakje in de vakjeArray een toString aan te
	// roepen
	public String toString(boolean eigenbord) {

		StringBuilder sb = new StringBuilder();

		for (int j = bordLengte - 1; j >= 0; j--) {

			for (int i = 0; i <= bordBreedte - 1; i++) {

				sb.append(giveVakje(i,j).toString(eigenbord));
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
					if (giveVakje(i+1,j+1).isBevatBoot()) {
						resultaat = true;
					}

				}
			}
		}

		return resultaat;
	}


	

}
