package nl.zwolle.gameClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vakje {
	
	
	
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

	// instace variablelen
	private boolean bevatBoot;
	private boolean beschoten;

	Boot boot;

	// getters and setters
	public boolean isBevatBoot() {
		return bevatBoot;
	}

	public void setBevatBoot(boolean bevatBoot, Boot boot) {
		this.bevatBoot = bevatBoot;
		this.boot = boot;
	}

	@OneToOne
	public Boot getBoot() {
		return boot;
	}

	public void setBoot(Boot boot) {
		this.boot = boot;
	}

	public void setBevatBoot(boolean bevatBoot) {
		this.bevatBoot = bevatBoot;
	}

	public boolean isBeschoten() {
		return beschoten;
	}

	public void setBeschoten(boolean beschoten) {
		this.beschoten = beschoten;

	}

	// Print de status van elk vakje uit. Als het bord van de speler zelf is dan
	// worden alle boten aangegeven in het bord. zo niet, dan alleen de
	// beschoten boten.
	public String toString(boolean eigenbord) {

		if (eigenbord) {
			if (bevatBoot) {
				return "$";
			} else {
				return "o";
			}
		} else {

			if (beschoten) {
				if (bevatBoot) {
					return "$";
				} else {
					return "x";
				}

			}
			return "o";
		}
	}

	// methoden

}
