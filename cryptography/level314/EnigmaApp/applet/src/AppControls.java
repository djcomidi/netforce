
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

class AppControls extends Panel {
	EnigmaPanel pnlEnigma;
	Choice[] rotors = new Choice[3];
	Choice[] initPositions = new Choice[3];
	Button resetButton;

	public AppControls(EnigmaPanel pnlEnigma) {
		setBackground(Color.GRAY);
		this.pnlEnigma = pnlEnigma;

		setLayout(new GridLayout(3, 4));

		rotors[0] = new Choice();
		rotors[1] = new Choice();
		rotors[2] = new Choice();
		for (int i = 1; i < 9; i++) {
			rotors[0].addItem(Integer.toString(i));
			rotors[1].addItem(Integer.toString(i));
			rotors[2].addItem(Integer.toString(i));
		}

		initPositions[0] = new Choice();
		initPositions[1] = new Choice();
		initPositions[2] = new Choice();
		for (int i = 0; i < 26; i++) {
			initPositions[0].addItem(Integer.toString(i));
			initPositions[1].addItem(Integer.toString(i));
			initPositions[2].addItem(Integer.toString(i));
		}

		resetButton = new Button("Reset");

		rotors[2].select(2);
		rotors[1].select(1);
		rotors[0].select(0);

		initPositions[2].select(0);
		initPositions[1].select(0);
		initPositions[0].select(0);

		add(resetButton);
		add(new Label("Rotor I:"));
		add(new Label("Rotor II:"));
		add(new Label("Rotor III:"));
		add(new Label("Rotor Type:", 2));
		add(rotors[0]);
		add(rotors[1]);
		add(rotors[2]);
		add(new Label("Startposition:", 2));
		add(initPositions[0]);
		add(initPositions[1]);
		add(initPositions[2]);

		for (int i = 0; i < 3; i++) {
			initPositions[i].setBackground(Color.WHITE);
			rotors[i].setBackground(Color.WHITE);
		}
	}

	@Override
	public boolean action(Event evt, Object what) {
		if (evt.target instanceof Button) {
			pnlEnigma.reset();
			return true;
		}
		if (evt.target instanceof Choice) {
			if (evt.target == rotors[0]) {
				pnlEnigma.changeRotor(0, rotors[0].getSelectedIndex() + 1);
			} else if (evt.target == rotors[1]) {
				pnlEnigma.changeRotor(1, rotors[1].getSelectedIndex() + 1);
			} else if (evt.target == rotors[2]) {
				pnlEnigma.changeRotor(2, rotors[2].getSelectedIndex() + 1);
			} else if (evt.target == initPositions[0]) {
				pnlEnigma.setRotorPosition(0, initPositions[0].getSelectedIndex());
			} else if (evt.target == initPositions[1]) {
				pnlEnigma.setRotorPosition(1, initPositions[1].getSelectedIndex());
			} else if (evt.target == initPositions[2]) {
				pnlEnigma.setRotorPosition(2, initPositions[2].getSelectedIndex());
			}
			return true;
		}
		return false;
	}
}
