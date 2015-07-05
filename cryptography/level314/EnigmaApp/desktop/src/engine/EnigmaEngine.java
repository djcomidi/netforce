package engine;

import common.IDisplay;
import common.IEngine;

public class EnigmaEngine implements IEngine {
	private IDisplay frame;
	private String downKey;
	private final Rotor[] rotors = new Rotor[3];
	private final Reflector reflector;
	private final CharLogger plainChars;
	private final CharLogger encodedChars;

	public EnigmaEngine() {
		rotors[2] = new Rotor(3, null);
		rotors[1] = new Rotor(2, rotors[2]);
		rotors[0] = new Rotor(1, rotors[1]);
		rotors[0].setInitialPosition(0);
		rotors[1].setInitialPosition(0);
		rotors[2].setInitialPosition(0);
		reflector = new Reflector();
		plainChars = new CharLogger();
		encodedChars = new CharLogger();
	}

	@Override
	public void setDisplay(IDisplay frame) {
		this.frame = frame;
	}

	@Override
	public void changeRotor(int rotorId, int newRotorId) {
		rotors[rotorId].setRotor(newRotorId);
		frame.updateDisplayValues();
	}

	@Override
	public void reset() {
		rotors[0].reset();
		rotors[1].reset();
		rotors[2].reset();
		plainChars.clear();
		encodedChars.clear();
		frame.updateDisplayValues();
	}

	@Override
	public void keyDown(String key) {
		downKey = key;
		processKey(key, false);
	}

	@Override
	public void keyUp(String key) {
		if (downKey.equals(key)) {
			processKey(key, true);
		}
		keyReset();
	}

	@Override
	public void keyReset() {
		downKey = "0";
		frame.turnOffBulbs();
	}

	@Override
	public void rotateUp(int rotorId) {
		rotors[rotorId].rotateUp();
		frame.updateDisplayValues();
	}

	@Override
	public void rotateDown(int rotorId) {
		rotors[rotorId].rotateDown();
		frame.updateDisplayValues();
	}

	@Override
	public int getCurrentPosition(int rotorId) {
		return rotors[rotorId].getCurrentPosition();
	}

	@Override
	public int getInitialPosition(int rotorId) {
		return rotors[rotorId].getInitialPosition();
	}

	@Override
	public void setInitialPosition(int rotorId, int position) {
		rotors[rotorId].setInitialPosition(position);
		reset();
	}

	private String encode(String key) {
		int code = key.charAt(0) - 'A';
		code = rotors[0].sendThru(code);
		code = rotors[1].sendThru(code);
		code = rotors[2].sendThru(code);
		code = reflector.reflect(code);
		code = rotors[2].sendBack(code);
		code = rotors[1].sendBack(code);
		code = rotors[0].sendBack(code);
		return Character.toString((char) (code + 'A'));
	}

	private void processKey(String key, boolean withRotate) {
		String eKey = encode(key);
		if (withRotate) {
			rotors[0].rotateUp();
			plainChars.add(key);
			encodedChars.add(eKey);
		}
		frame.turnOnBulb(eKey);
		frame.updateDisplayValues();
	}

	@Override
	public String getPlainText() {
		return plainChars.getText();
	}

	@Override
	public String getEncodedText() {
		return encodedChars.getText();
	}

	@Override
	public void setCurrentPosition(int rotorId, int position) {
		rotors[rotorId].setCurrentPosition(position);
	}
}
