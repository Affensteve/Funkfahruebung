package kom.feuerwehr.core;

public enum Hersteller {

	MAN("MAN"), IVECO("Iveco"), Ford("Ford"), Merccedes("Mercedes"), VW("Volkswagen");

	private String displayName;

	private Hersteller(String displayName) {
		this.displayName = displayName;
	}

	public String displayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
