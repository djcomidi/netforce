
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

public class EnigmaApp extends Applet {
	AppControls controls;

	@Override
	public void init() {
		setLayout(new BorderLayout());
		Image img = createImage(getWidth(), getHeight());
		Graphics g = img.getGraphics();

		EnigmaPanel pnlEnigma = new EnigmaPanel(img, g);
		controls = new AppControls(pnlEnigma);

		add(BorderLayout.NORTH, controls);
		add(BorderLayout.CENTER, pnlEnigma);
	}

	@Override
	public void start() {
		this.controls.setEnabled(true);
	}

	@Override
	public void stop() {
		this.controls.setEnabled(false);
	}

	@Override
	public boolean handleEvent(Event evt) {
		if (evt.id == Event.WINDOW_DESTROY) {
			System.exit(0);
		}
		return false;
	}

	public static void main(String[] args) {
		Frame localFrame = new Frame("NetForce Rotor Machine");
		EnigmaApp enigmaApp = new EnigmaApp();

		enigmaApp.init();
		enigmaApp.start();

		localFrame.add(BorderLayout.CENTER, enigmaApp);
		localFrame.setSize(300, 300);
		localFrame.setVisible(true);
	}
}

