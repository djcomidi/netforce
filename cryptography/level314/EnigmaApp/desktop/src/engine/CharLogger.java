package engine;

public class CharLogger {

	private StringBuffer sb;

	public CharLogger() {
		sb = new StringBuffer();
	}

	public void add(String s) {
		sb = sb.append(s);
		sb.append(" ");
		while (sb.length() > 50) {
			sb.deleteCharAt(0);
		}
	}

	public void clear() {
		sb.delete(0, sb.length());
	}

	public String getText() {
		return sb.toString();
	}
}
