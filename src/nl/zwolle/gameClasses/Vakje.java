package nl.zwolle.gameClasses;

import javax.persistence.Entity;

@Entity
public class Vakje {

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
