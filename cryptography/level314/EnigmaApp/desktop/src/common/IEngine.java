package common;

public interface IEngine {
	public void setDisplay(IDisplay frame);

	public int getInitialPosition(int rotorId);

	public int getCurrentPosition(int rotorId);

	public String getPlainText();

	public String getEncodedText();

	public void reset();

	public void changeRotor(int rotorId, int newRotorId);

	public void setInitialPosition(int rotorId, int position);

	public void setCurrentPosition(int rotorId, int position);

	public void rotateUp(int rotorId);

	public void rotateDown(int rotorId);

	public void keyDown(String key);

	public void keyUp(String key);

	public void keyReset();
}
