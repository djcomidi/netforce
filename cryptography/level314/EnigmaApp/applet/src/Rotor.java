import java.awt.Color;
import java.awt.Graphics;

public class Rotor {
	public static final int ROTORSIZE = 26;
	int initialPosition;
	int currentPosition;
	int pegPosition;
	int[] rotor = new int[26];
	int[] inverseRotor = new int[26];
	boolean buttonUp1;
	boolean buttonUp2;
	Rotor nextRotor;
	public static final int[] PEGS = { 0, 24, 12, 3, 17, 7, 16, 20, 1 };
	public static final int[][] SETTINGS = {
			{ 0 },
			{ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 },
			{ 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 },
			{ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 },
			{ 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 },
			{ 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 },
			{ 9, 15, 6, 21, 14, 20, 12, 5, 24, 16, 1, 4, 13, 7, 25, 17, 3, 10, 0, 18, 23, 11, 8, 2, 19, 22 },
			{ 13, 25, 9, 7, 6, 17, 2, 23, 12, 24, 18, 22, 1, 14, 20, 5, 0, 8, 21, 11, 15, 4, 10, 16, 3, 19 },
			{ 5, 10, 16, 7, 19, 11, 23, 14, 2, 1, 9, 18, 15, 3, 25, 17, 0, 12, 4, 22, 13, 8, 20, 24, 6, 21 } };

	public Rotor(int id, Rotor nextRotor) {
		setRotor(id);
		this.buttonUp1 = this.buttonUp2 = true;
		this.nextRotor = nextRotor;
	}

	private void setRotor(int id) {
		this.rotor = SETTINGS[id];
		this.pegPosition = PEGS[id];
		for (int i = 0; i < 26; i++) {
			this.inverseRotor[this.rotor[i]] = i;
		}
	}

	public void change(int id, Rotor nextRotor) {
		setRotor(id);
		this.buttonUp1 = this.buttonUp2 = true;
		this.nextRotor = nextRotor;
	}

	public void rotate(boolean rotateUp) {
		if (rotateUp) {
			currentPosition = ((currentPosition + 1) % 26);
		} else {
			currentPosition -= 1;
			if (currentPosition < 0) {
				currentPosition = (26 - 1);
			}
		}
		if ((rotateUp) && (nextRotor != null) && (currentPosition == pegPosition)) {
			nextRotor.rotate(true);
		}
	}

	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

	public void reset() {
		this.currentPosition = this.initialPosition;
	}

	public int send(int keyCode, boolean rotateUp) {
		if (rotateUp) {
			return this.rotor[((keyCode + currentPosition) % 26)];
		}
		return (this.inverseRotor[keyCode] - currentPosition + 26) % 26;
	}

	public void draw(Graphics g, int x, int y) {
		int h = 20;
		int w = 15;

		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);

		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(currentPosition), x + 1, y + h - 3);

		drawButtons(g, x + w + 5, y - 5);
	}

	private void drawButtons(Graphics g, int x, int y) {
		g.setColor(Color.LIGHT_GRAY);
		g.fill3DRect(x, y, 10, 10, buttonUp1);

		g.setColor(Color.BLACK);
		int[] xPoints = new int[] { x + 2, x + 5, x + 8 };
		int[] yPoints = new int[] { y + 8, y + 2, y + 8 };
		g.fillPolygon(xPoints, yPoints, 3);

		g.setColor(Color.LIGHT_GRAY);
		g.fill3DRect(x, y + 20, 10, 10, buttonUp2);

		g.setColor(Color.BLACK);
		xPoints = new int[] { x + 2, x + 5, x + 8 };
		yPoints = new int[] { y + 22, y + 28, y + 22 };
		g.fillPolygon(xPoints, yPoints, 3);
	}

	public boolean mouseDown(int x, int y, int rotorX, int rotorY) {
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY - 5) && (y <= rotorY + 5)) {
			buttonUp1 = false;
			return true;
		}
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY + 15) && (y <= rotorY + 25)) {
			buttonUp2 = false;
			return true;
		}

		buttonUp1 = buttonUp2 = true;
		return false;
	}

	public boolean mouseDrag(int x, int y, int rotorX, int rotorY) {
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY - 5) && (y <= rotorY + 5)) {
			buttonUp1 = false;
			return true;
		}
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY + 15) && (y <= rotorY + 25)) {
			buttonUp2 = false;
			return true;
		}
		buttonUp1 = buttonUp2 = true;
		return true;
	}

	public boolean mouseUp(int x, int y, int rotorX, int rotorY) {
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY - 5) && (y <= rotorY + 5)) {
			buttonUp1 = true;
			rotate(true);
			return true;
		}
		if ((x >= rotorX + 20) && (x <= rotorX + 30) && (y >= rotorY + 15) && (y <= rotorY + 25)) {
			buttonUp2 = true;
			rotate(false);
			return true;
		}
		buttonUp1 = buttonUp2 = true;
		return true;
	}
}
