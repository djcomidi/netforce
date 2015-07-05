package engine;

import common.Constants;

public class Rotor {

	private final Rotor nextRotor;
	private int[] rotor = new int[26];
	private int pegPosition = 0;
	private final int[] inverseRotor = new int[26];
	private int currentPosition = 0;
	private int initialPosition = 0;

	public Rotor(int id, Rotor nextRotor) {
		setRotor(id);
		this.nextRotor = nextRotor;
	}

	public void setRotor(int id) {
		this.rotor = Constants.SETTINGS[id - 1];
		this.pegPosition = Constants.PEGS[id - 1];
		for (int i = 0; i < 26; i++) {
			this.inverseRotor[this.rotor[i]] = i;
		}
	}

	public void rotateUp() {
		currentPosition = ((currentPosition + 1) % 26);
		if (nextRotor != null && currentPosition == pegPosition) {
			nextRotor.rotateUp();
		}
	}

	public void rotateDown() {
		currentPosition = (currentPosition + 25) % 26;
	}

	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

	public void reset() {
		this.currentPosition = this.initialPosition;
	}

	public int sendThru(int code) {
		return this.rotor[(code + currentPosition) % 26];
	}

	public int sendBack(int code) {
		return (this.inverseRotor[code] - currentPosition + 26) % 26;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public int getInitialPosition() {
		return initialPosition;
	}

	public void setCurrentPosition(int position) {
		this.currentPosition = position;
	}
}
