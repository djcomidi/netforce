import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.EnigmaFrame;
import common.IEngine;
import engine.EnigmaEngine;

public class EnigmaDesktop {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		IEngine engine = new EnigmaEngine();
		EnigmaFrame ef = new EnigmaFrame(engine);
		ef.setLocationRelativeTo(null);
		ef.setVisible(true);
	}
}
