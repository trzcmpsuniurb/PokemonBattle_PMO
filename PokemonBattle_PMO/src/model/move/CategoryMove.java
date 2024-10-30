package model.move;

public enum CategoryMove {
	PHYSICAL,
	SPECIAL,
	STATUT;
	
	/**
	 * Return a CategoryMove that correspond to the specified string
	 * @param s
	 * @return
	 */
	public static CategoryMove fromString(String s) {
		switch (s.trim()) {
		case "physical":
			return CategoryMove.PHYSICAL;
		case "statut":
			return CategoryMove.STATUT;
		case "special":
			return CategoryMove.SPECIAL;
		default:
			return null;
		}
	}
}	