import java.awt.Color;
import java.awt.Graphics;

public class Keyboard {

	String[] rows = new String[3];
	boolean keyIsDown;
	char downKey;
	char lightKey;
	EnigmaPanel pnlEnigma;

	public Keyboard(EnigmaPanel pnlEnigma) {
		rows[0] = new String("QWERTZUIO");
		rows[1] = new String("ASDFGHJK");
		rows[2] = new String("PYXCVBNML");
		keyIsDown = false;
		downKey = '0';
		lightKey = '0';
		this.pnlEnigma = pnlEnigma;
	}

	public void setLight(char c) {
		this.lightKey = c;
	}

	public void drawKeypad(Graphics g, int x, int y) {
		drawPadRow(g, x, y, 30, 30, rows[0].length(), 2, rows[0]);
		y += 32;
		x += 15;
		drawPadRow(g, x, y, 30, 30, rows[1].length(), 2, rows[1]);
		y += 32;
		x -= 15;
		drawPadRow(g, x, y, 30, 30, rows[2].length(), 2, rows[2]);
	}

	private void drawPadRow(Graphics g, int x, int y, int height, int width, int nColumns, int gap, String keys) {
		for (int i = 0; i < nColumns; i++) {
			g.setColor(Color.LIGHT_GRAY);
			if ((this.keyIsDown) && (keys.charAt(i) == this.downKey)) {
				g.fill3DRect(x + i * (width + gap), y, width, height, false);
			} else {
				g.fill3DRect(x + i * (width + gap), y, width, height, true);
			}
			g.setColor(Color.BLACK);
			g.drawString(keys.substring(i, i + 1), x + i * (width + gap) + (width + gap) / 2 - 4, y
					+ height - height / 4);
		}
	}

	public void drawKeyDisplay(Graphics g, int x, int y) {
		drawDisplayRow(g, x, y, 30, 30, rows[0].length(), 2, rows[0]);
		y += 32;
		x += 15;
		drawDisplayRow(g, x, y, 30, 30, rows[1].length(), 2, rows[1]);
		y += 32;
		x -= 15;
		drawDisplayRow(g, x, y, 30, 30, rows[2].length(), 2, rows[2]);
	}

	private void drawDisplayRow(Graphics g, int x, int y, int height, int width, int rows, int gap, String keys) {
		for (int i = 0; i < rows; i++) {
			if ((this.keyIsDown) && (keys.charAt(i) == lightKey)) {
				g.setColor(Color.YELLOW);
				g.fillArc(x + i * (width + gap), y, width, height, 0, 360);
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.BLACK);
				g.fillArc(x + i * (width + gap), y, width, height, 0, 360);
				g.setColor(Color.WHITE);
			}
			g.drawString(keys.substring(i, i + 1), x + i * (width + gap) + (width + gap) / 2 - 4, y
					+ height - height / 4);
		}
	}

	private boolean findKey(int x, int y, int keyboardX, int keyboardY, int height, int width, int nColumns,
			int gap, String keys) {
		for (int j = 0; j < nColumns; j++) {
			int i = keyboardX + j * (width + gap);

			if ((x >= i) && (x <= i + width) && (y >= keyboardY) && (y <= keyboardY + height)) {
				keyIsDown = true;
				downKey = keys.charAt(j);
				return true;
			}
		}
		return false;
	}

	public boolean mouseDown(int x, int y, int keyboardX, int keyboardY) {
		boolean isMouseDown = false;
		isMouseDown |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[0].length(), 2, rows[0]);
		keyboardY += 32;
		keyboardX += 15;
		isMouseDown |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[1].length(), 2, rows[1]);
		keyboardY += 32;
		keyboardX -= 15;
		isMouseDown |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[2].length(), 2, rows[2]);
		if (!isMouseDown)
			resetDownKey();
		else {
			pnlEnigma.processChar(this.downKey, false);
		}
		return isMouseDown;
	}

	public boolean mouseDrag(int x, int y, int keyboardX, int keyboardY) {
		boolean isMouseDragging = false;
		isMouseDragging |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[0].length(), 2, rows[0]);
		keyboardY += 32;
		keyboardX += 15;
		isMouseDragging |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[1].length(), 2, rows[1]);
		keyboardY += 32;
		keyboardX -= 15;
		isMouseDragging |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[2].length(), 2, rows[2]);
		if (!isMouseDragging)
			resetDownKey();
		else {
			pnlEnigma.processChar(downKey, false);
		}
		return isMouseDragging;
	}

	public boolean mouseUp(int x, int y, int keyboardX, int keyboardY) {
		boolean isMouseUp = false;
		isMouseUp |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[0].length(), 2, rows[0]);
		keyboardY += 32;
		keyboardX += 15;
		isMouseUp |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[1].length(), 2, rows[1]);
		keyboardY += 32;
		keyboardX -= 15;
		isMouseUp |= findKey(x, y, keyboardX, keyboardY, 30, 30, rows[2].length(), 2, rows[2]);
		if (isMouseUp) {
			pnlEnigma.processChar(downKey, true);
			resetDownKey();
		}
		return isMouseUp;
	}

	public void setDownKey(char c) {
		downKey = c;
		keyIsDown = true;
	}

	public void resetDownKey() {
		downKey = '0';
		keyIsDown = false;
	}
}
