package netforcechallenge;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class SecurityDatabase implements Serializable {
	private final Hashtable<String, User> a = new Hashtable<>();
	public static int b;

	public static final long serialVersionUID = 8336722522283464084L;

	public void addUser(User user) {
		a.put(user.getName().toLowerCase(), user);
	}

	public void removeUser(User user) {
		a.remove(user.getName().toLowerCase());
	}

	public void removeUserByName(String name) {
		a.remove(name.toLowerCase());
	}

	public User getUserByName(String name) {
		return a.get(name.toLowerCase());
	}

	public Vector<String> getUsernames() {
		Vector<String> names = new Vector<>();
		for (String name : a.keySet()) {
			names.add(name);
		}
		return names;
	}

	public Permissions getPermissions(String name) {
		User user = a.get(name.toLowerCase());
		if (user != null)
			return user.getPermissions();
		return null;
	}
}
