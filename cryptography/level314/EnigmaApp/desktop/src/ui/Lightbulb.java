package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Lightbulb extends JButton implements ActionListener {
	private boolean isTurnedOn = false;
	private static final Color DEFAULT_BG = (new JButton()).getBackground();

	public Lightbulb(String txt) {
		super();
		setText(txt);
		Font font = this.getFont();
		setFont(new Font(font.getFontName(), Font.BOLD, font.getSize() + 4));
		setForeground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(48, 31));
		setMinimumSize(new Dimension(48, 31));
		turnOff();
	}

	public void turnOn() {
		isTurnedOn = true;
		setBackground(Color.YELLOW);
		setForeground(Color.BLACK);
	}

	public void turnOff() {
		isTurnedOn = false;
		setBackground(DEFAULT_BG);
		setForeground(Color.LIGHT_GRAY);
	}

	public void toggle() {
		if (isTurnedOn) {
			turnOff();
		} else {
			turnOn();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toggle();
	}

}
