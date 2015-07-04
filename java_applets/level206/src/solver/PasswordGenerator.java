package solver;

import java.math.BigInteger;
import java.util.Iterator;

public class PasswordGenerator implements Iterator<String> {
	private final String alfabet;
	private final int length;
	private final BigInteger maxCombinations;
	private BigInteger index;

	public PasswordGenerator(int length, String charset) {
		this.length = length;
		this.alfabet = this.buildAlphabet(charset);
		maxCombinations = BigInteger.valueOf(alfabet.length()).pow(length);
		index = BigInteger.ZERO;
	}

	private String buildAlphabet(String charset) {
		StringBuilder sb = new StringBuilder();
		for (char c : charset.toCharArray()) {
			if (c == 'a') {
				sb.append("abcdefghijklmnopqrstuvwxyz");
			} else if (c == 'A') {
				sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			} else if (c == '1') {
				sb.append("0123456789");
			} else if (c == '!') {
				sb.append('!');
			}
		}
		return sb.toString();
	}

	private String passwordFromIndex(BigInteger idx) {
		BigInteger size = BigInteger.valueOf(alfabet.length());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			BigInteger[] qr = idx.divideAndRemainder(size);
			sb.append(alfabet.charAt(qr[1].intValue()));
			idx = qr[0];
		}
		return sb.reverse().toString();
	}

	@Override
	public boolean hasNext() {
		return index.compareTo(maxCombinations) < 0;
	}

	@Override
	public String next() {
		String pwd = passwordFromIndex(index);
		index = index.add(BigInteger.ONE);
		return pwd;
	}

	@Override
	public void remove() {
	}

	public static void test() {
		PasswordGenerator pgen = new PasswordGenerator(3, "Aa1");
		char first = '?';
		while (pgen.hasNext()) {
			String pwd = pgen.next();
			if (first != pwd.charAt(0)) {
				first = pwd.charAt(0);
				System.out.println(first);
			}
		}
	}
}
