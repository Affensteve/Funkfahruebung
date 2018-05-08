package kom.feuerwehr.core;

public enum Klassifizierung {

	TLF_3000("TLF 3000"), TSF("TSF"), MTW("MTW"), LF_8("LF 8"), RW_1("RW 1"), LF_10("LF 10"), TLF_16_25(
			"TLF 16/25"), LF_20_16("LF 20/16"), ELW("ELW 1");

	private String displayName;

	private Klassifizierung(String displayName) {
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
