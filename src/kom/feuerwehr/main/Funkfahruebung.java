package kom.feuerwehr.main;

import javax.swing.JOptionPane;

import kom.feuerwehr.gui.Gui;

public class Funkfahruebung {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog(null, "Geben Sie die Ip-Adresse des Servers ein: ",
				"Funkfahrübung by ©S.Kunz\n", 1);
		if (str != null) {
			Gui gui = new Gui();
			System.out.println(str);
			gui.setIPAdresse(str);
		} else {
			Gui gui = new Gui();
			gui.setIPAdresse("");
		}
	}
}
