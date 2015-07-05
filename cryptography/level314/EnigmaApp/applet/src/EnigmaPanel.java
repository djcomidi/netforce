import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.Vector;

public class EnigmaPanel extends Panel {
	Image offscreenImage;
	Graphics offscreenGraphics;
	Keyboard keyboard;
	Rotor[] rotors = new Rotor[3];
	Reflector reflector;
	char lastLetter;
	char lastEncodedLetter;
	Vector<Character> input;
	Vector<Character> output;

	public EnigmaPanel(Image offscreenImage, Graphics offscreenGraphics) {
		this.offscreenGraphics = offscreenGraphics;
		this.offscreenImage = offscreenImage;

		setBackground(Color.WHITE);

		keyboard = new Keyboard(this);

		rotors[2] = new Rotor(3, null);
		rotors[1] = new Rotor(2, rotors[2]);
		rotors[0] = new Rotor(1, rotors[1]);

		rotors[0].setInitialPosition(0);
		rotors[1].setInitialPosition(0);
		rotors[2].setInitialPosition(0);

		reflector = new Reflector(1);

		input = new Vector<>();
		output = new Vector<>();
	}

	public void changeRotor(int id, int newRotorId) {
		if (id == 2)
			rotors[id].change(newRotorId, null);
		else {
			rotors[id].change(newRotorId, rotors[(id + 1)]);
		}
		repaint();
	}

	public void setRotorPosition(int id, int position) {
		rotors[id].setInitialPosition(position);
	}

	public void reset() {
		for (int i = 0; i < 3; i++) {
			rotors[i].reset();
		}
		input.removeAllElements();
		output.removeAllElements();
		repaint();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		try {
			Thread.sleep(1L);
		} catch (InterruptedException e) {
		}
		offscreenGraphics.clearRect(0, 0, getWidth(), getHeight());

		offscreenGraphics.setColor(new Color(132, 77, 14));
		offscreenGraphics.fillRect(1, 1, 305, 374);
		offscreenGraphics.setColor(new Color(217, 217, 250));
		offscreenGraphics.drawRect(0, 0, 306, 375);

		keyboard.drawKeypad(offscreenGraphics, 10, 265);
		keyboard.drawKeyDisplay(offscreenGraphics, 10, 60);

		offscreenGraphics.setColor(Color.BLACK);
		offscreenGraphics.fillRect(10, 10, 287, 40);

		offscreenGraphics.setColor(new Color(217, 217, 250));
		offscreenGraphics.drawRect(10, 10, 287, 40);

		rotors[0].draw(offscreenGraphics, 60, 20);
		rotors[1].draw(offscreenGraphics, 150, 20);
		rotors[2].draw(offscreenGraphics, 240, 20);

		offscreenGraphics.setColor(Color.WHITE);
		offscreenGraphics.fillOval(85, 237, 136, 22);
		offscreenGraphics.setColor(Color.BLACK);
		offscreenGraphics.drawOval(85, 237, 136, 22);

		Font defaultFont = offscreenGraphics.getFont();
		offscreenGraphics.setFont(new Font("TimesRoman", 3, 18));
		offscreenGraphics.drawString("Net-Force", 118, 254);
		offscreenGraphics.setFont(new Font("TimesRoman", 3, 10));
		offscreenGraphics.drawString("Created by plens", 230, 372);
		offscreenGraphics.setFont(defaultFont);

		offscreenGraphics.setColor(Color.WHITE);
		offscreenGraphics.fillRect(10, 160, 286, 70);
		drawResults(offscreenGraphics, 10, 165);

		g.drawImage(offscreenImage, 0, 0, this);
	}

	private void drawResults(Graphics g, int x, int y) {
		String plainInput = new String("");
		String encodedOutput = new String("");

		g.setColor(new Color(217, 217, 250));
		g.drawRect(x, y - 5, 285, 70); // x y w h
		g.setColor(new Color(84, 84, 84));
		g.drawString("Original Text (last 25 characters):", x + 5, y + 10);
		g.drawString("Encoded Text (last 25 characters): ", x + 5, y + 45);
		g.setColor(Color.BLACK);
		if (input.size() > 0) {
			for (int i = input.size() - 1; (i >= input.size() - 25) && (i >= 0); i--) {
				plainInput = input.elementAt(i).toString().concat(plainInput);
				encodedOutput = output.elementAt(i).toString().concat(encodedOutput);
			}
			g.drawString(plainInput, x + 5, y + 25);
			g.drawString(encodedOutput, x + 5, y + 60);
		}
	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {
		boolean needsRepaint = false;
		if ((x >= 0) && (x < 300) && (y >= 265) && (y < 365))
			needsRepaint = keyboard.mouseDown(x, y, 10, 265);
		else if ((x >= 0) && (x < 150) && (y >= 0) && (y < 60))
			needsRepaint = rotors[0].mouseDown(x, y, 60, 20);
		else if ((x >= 150) && (x < 240) && (y >= 0) && (y < 60))
			needsRepaint = rotors[1].mouseDown(x, y, 150, 20);
		else if ((x >= 240) && (x < 300) && (y >= 0) && (y < 60)) {
			needsRepaint = rotors[2].mouseDown(x, y, 240, 20);
		}
		if (needsRepaint) {
			repaint();
		}
		return true;
	}

	@Override
	public boolean mouseUp(Event evt, int x, int y) {
		boolean needsRepaint = false;
		if ((x >= 0) && (x < 300) && (y >= 265) && (y < 365))
			needsRepaint = keyboard.mouseUp(x, y, 10, 265);
		else if ((x >= 0) && (x < 150) && (y >= 0) && (y < 60))
			needsRepaint = rotors[0].mouseUp(x, y, 60, 20);
		else if ((x >= 150) && (x < 240) && (y >= 0) && (y < 60))
			needsRepaint = rotors[1].mouseUp(x, y, 150, 20);
		else if ((x >= 240) && (x < 300) && (y >= 0) && (y < 60)) {
			needsRepaint = rotors[2].mouseUp(x, y, 240, 20);
		}
		if (needsRepaint) {
			repaint();
		}
		return true;
	}

	@Override
	public boolean mouseDrag(Event evt, int x, int y) {
		boolean needsRepaint = false;
		needsRepaint |= this.keyboard.mouseDrag(x, y, 10, 160);
		needsRepaint |= this.rotors[2].mouseDrag(x, y, 60, 20);
		needsRepaint |= this.rotors[1].mouseDrag(x, y, 150, 20);
		needsRepaint |= this.rotors[0].mouseDrag(x, y, 240, 20);
		if (needsRepaint) {
			repaint();
		}
		return true;
	}

	@Override
	public boolean keyDown(Event evt, int key) {
		char c = (char) key;
		if ((c <= 'z') && (c >= 'a')) {
			c = (char) (c - 'a' + 65);
		}
		if ((c > 'Z') || (c < 'A')) {
			return false;
		}
		keyboard.setDownKey(c);
		processChar(c, false);
		repaint();
		return true;
	}

	@Override
	public boolean keyUp(Event evt, int key) {
		char c = (char) key;
		if ((c <= 'z') && (c >= 'a')) {
			c = (char) (c - 'a' + 65);
		}
		if ((c > 'Z') || (c < 'A')) {
			return false;
		}
		keyboard.setDownKey(c);
		processChar(c, true);
		repaint();
		keyboard.resetDownKey();
		return true;
	}

	public void processChar(char c, boolean noSimulation) {
		int cIndex = 0;
		if (c == lastLetter) {
			keyboard.setLight(lastEncodedLetter);
			if (noSimulation) {
				rotors[0].rotate(true);
				input.addElement(new Character(c));
				output.addElement(new Character(lastEncodedLetter));
				lastEncodedLetter = lastLetter = '0';
			}
			return;
		}
		lastLetter = c;
		cIndex = c - 'A';
		if ((cIndex >= 0) && (cIndex <= 25)) {
			for (int i = 0; i < 3; i++) {
				cIndex = rotors[i].send(cIndex, true);
			}
			cIndex = reflector.send(cIndex);
			for (int i = 2; i >= 0; i--) {
				cIndex = rotors[i].send(cIndex, false);
			}
			lastEncodedLetter = ((char) (cIndex + 65));
			if (noSimulation) {
				rotors[0].rotate(true);
				input.addElement(new Character(c));
				output.addElement(new Character((char) (cIndex + 65)));
				lastEncodedLetter = lastLetter = '0';
			}
		}
		keyboard.setLight((char) (cIndex + 65));
	}
}
