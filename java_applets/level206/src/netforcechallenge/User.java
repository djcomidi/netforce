package netforcechallenge;

import java.io.Serializable;

public class User implements Serializable {
	private final String a;
	private final byte[] b;
	private final Permissions c;
	public static int d;

	public static final long serialVersionUID = -3774733628153127126L;

	public User(String name, byte[] pwdBytes, Permissions permissions) {
		this.a = name;
		this.b = pwdBytes;
		this.c = permissions;
	}

	public String getName() {
		return this.a;
	}

	public byte[] getPwdBytes() {
		return this.b;
	}

	public Permissions getPermissions() {
		return this.c;
	}

}
