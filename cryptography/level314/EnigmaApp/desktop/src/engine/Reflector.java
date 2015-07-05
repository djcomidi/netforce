package engine;

import java.util.Vector;

public class Reflector {
	private final Vector<Integer> reflection;

	public Reflector() {
		reflection = new Vector<>();
		reflection.add(24);
		reflection.add(17);
		reflection.add(20);
		reflection.add(7);
		reflection.add(16);
		reflection.add(18);
		reflection.add(11);
		reflection.add(3);
		reflection.add(15);
		reflection.add(23);
		reflection.add(13);
		reflection.add(6);
		reflection.add(14);
		reflection.add(10);
		reflection.add(12);
		reflection.add(8);
		reflection.add(4);
		reflection.add(1);
		reflection.add(5);
		reflection.add(25);
		reflection.add(2);
		reflection.add(22);
		reflection.add(21);
		reflection.add(9);
		reflection.add(0);
		reflection.add(19);
	}

	public int reflect(int code) {
		return reflection.get(code);
	}
}
