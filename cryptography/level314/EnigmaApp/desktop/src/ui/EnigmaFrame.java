package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.Constants;
import common.IDisplay;
import common.IEngine;

public class EnigmaFrame extends JFrame implements ActionListener, IDisplay {

	private final IEngine engine;
	private Vector<Lightbulb> lightbulbs;
	private JButton btnReset;
	private Vector<JComboBox<Integer>> rotorTypes;
	private Vector<JComboBox<Integer>> initPositions;
	private Vector<CurrentPositionSpinner> currentPositions;
	private JTextField txtPlain;
	private JTextField txtEncoded;

	public EnigmaFrame(IEngine engine) {
		super("Net-Force Enigma Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.engine = engine;
		this.engine.setDisplay(this);
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		pnlMain.add(getSetupPanel());
		pnlMain.add(getInfoPanel());
		pnlMain.add(getOutputPanel());
		pnlMain.add(getKeyboardPanel());
		this.getContentPane().add(pnlMain);
		updateDisplayValues();
		pack();
		this.setResizable(false);
	}

	private JPanel getSetupPanel() {
		JPanel pnl = new JPanel(new GridLayout(3, 4, 2, 2));
		pnl.setBorder(BorderFactory.createTitledBorder("Setup"));
		// row 1
		btnReset = new JButton("RESET");
		btnReset.addActionListener(this);
		Font font = btnReset.getFont();
		btnReset.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		btnReset.setForeground(Color.RED);
		pnl.add(btnReset);
		pnl.add(new JLabel("Rotor I", SwingConstants.CENTER));
		pnl.add(new JLabel("Rotor II", SwingConstants.CENTER));
		pnl.add(new JLabel("Rotor III", SwingConstants.CENTER));
		// row 2
		pnl.add(new JLabel("Rotor Type:", SwingConstants.RIGHT));
		rotorTypes = new Vector<>();
		for (int i = 0; i < 3; i++) {
			rotorTypes.add(new JComboBox<>(Constants.ROTORTYPES));
			rotorTypes.get(i).setSelectedIndex(i);
			rotorTypes.get(i).addItemListener(new RotorTypeListener(i));
			pnl.add(rotorTypes.get(i));
		}
		// row 3
		pnl.add(new JLabel("Starting Position:", SwingConstants.RIGHT));
		initPositions = new Vector<>();
		for (int i = 0; i < 3; i++) {
			initPositions.add(new JComboBox<>(Constants.INITPOSITIONS));
			initPositions.get(i).addItemListener(new InitPositionListener(i));
			pnl.add(initPositions.get(i));
		}
		return pnl;
	}

	private JPanel getInfoPanel() {
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createTitledBorder("Info"));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		// positions
		JPanel pnlPositions = new JPanel(new GridLayout(1, 4, 5, 5));
		pnlPositions.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, pnlPositions.getBackground()));
		pnlPositions.add(new JLabel("Current Position:", SwingConstants.RIGHT));
		currentPositions = new Vector<>();
		for (int i = 0; i < 3; i++) {
			currentPositions.add(new CurrentPositionSpinner(engine, i));
			pnlPositions.add(currentPositions.get(i));
		}
		pnl.add(pnlPositions);
		// lightbulbs
		lightbulbs = new Vector<>();
		for (char[] row : Constants.KEYBOARD) {
			JPanel pnlRow = new JPanel();
			pnlRow.setLayout(new BoxLayout(pnlRow, BoxLayout.X_AXIS));
			pnlRow.add(Box.createHorizontalGlue());
			for (char key : row) {
				Lightbulb bulb = new Lightbulb(Character.toString(key));
				lightbulbs.add(bulb);
				pnlRow.add(bulb);
			}
			pnlRow.add(Box.createHorizontalGlue());
			pnl.add(pnlRow);
		}
		return pnl;
	}

	private JPanel getOutputPanel() {
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createTitledBorder("Output"));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.add(new JLabel("Original Text (last 25 characters):"));
		txtPlain = new JTextField("");
		Font font = txtPlain.getFont();
		font = new Font(Font.MONOSPACED, font.getStyle(), font.getSize() + 2);
		txtPlain.setFont(font);
		txtPlain.setEditable(false);
		txtPlain.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(txtPlain);
		pnl.add(new JLabel("Encoded Text (last 25 characters):"));
		txtEncoded = new JTextField("");
		txtEncoded.setFont(font);
		txtEncoded.setEditable(false);
		txtEncoded.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(txtEncoded);
		return pnl;
	}

	private JPanel getKeyboardPanel() {
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createTitledBorder("Keyboard"));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		for (char[] row : Constants.KEYBOARD) {
			JPanel pnlRow = new JPanel();
			pnlRow.setLayout(new BoxLayout(pnlRow, BoxLayout.X_AXIS));
			pnlRow.add(Box.createHorizontalGlue());
			for (char key : row) {
				pnlRow.add(new KeyButton(engine, key));
			}
			pnlRow.add(Box.createHorizontalGlue());
			pnl.add(pnlRow);
		}
		return pnl;
	}

	@Override
	public void turnOnBulb(String key) {
		for (Lightbulb bulb : lightbulbs) {
			bulb.turnOff();
			if (bulb.getText().equals(key)) {
				bulb.turnOn();
			}
		}
	}

	@Override
	public void turnOffBulbs() {
		for (Lightbulb bulb : lightbulbs) {
			bulb.turnOff();
		}
	}

	@Override
	public void updateDisplayValues() {
		for (int i = 0; i < 3; i++) {
			initPositions.get(i).setSelectedItem(engine.getInitialPosition(i));
			currentPositions.get(i).setValue(engine.getCurrentPosition(i));
		}
		txtPlain.setText(engine.getPlainText());
		txtEncoded.setText(engine.getEncodedText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReset) {
			engine.reset();
		}
	}

	private class RotorTypeListener implements ItemListener {
		int rotorId = 0;

		public RotorTypeListener(int rotorId) {
			this.rotorId = rotorId;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				int newRotorId = (int) e.getItem();
				engine.changeRotor(rotorId, newRotorId);
				updateDisplayValues();
			}
		}
	}

	private class InitPositionListener implements ItemListener {
		int rotorId = 0;

		public InitPositionListener(int rotorId) {
			this.rotorId = rotorId;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				int position = (int) e.getItem();
				engine.setInitialPosition(rotorId, position);
				updateDisplayValues();
			}
		}
	}

}
