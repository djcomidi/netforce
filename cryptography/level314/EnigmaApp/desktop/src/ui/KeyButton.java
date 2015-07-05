package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import common.IEngine;

public class KeyButton extends JButton implements MouseListener {
	private final IEngine engine;

	public KeyButton(IEngine engine, char key) {
		this.engine = engine;
		setText(Character.toString(key));
		Font font = this.getFont();
		setFont(new Font(font.getFontName(), Font.BOLD, font.getSize() + 4));
		setPreferredSize(new Dimension(48, 31));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		engine.keyReset();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		engine.keyDown(this.getText());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		engine.keyUp(this.getText());
	}

}
