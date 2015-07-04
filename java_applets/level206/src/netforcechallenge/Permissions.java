package netforcechallenge;

import java.io.Serializable;

public class Permissions implements Serializable {
	public static final int USER = 1;
	public static final int ADMIN = 2;
	public static final int SUPERUSER = 4;
	private int d;

	public static final long serialVersionUID = -3617511225047885103L;

	public Permissions() {
		this(0);
	}

	public Permissions(int levels) {
		this.d = levels;
	}

	public void enable(int level) {
		this.d |= level;
	}

	public void disable(int level) {
		this.d &= ~level;
	}

	public boolean isUser() {
		return (this.d & USER) == USER;
	}

	public boolean isAdmin() {
		return (this.d & ADMIN) == ADMIN;
	}

	public boolean isSuperUser() {
		return (this.d & SUPERUSER) == SUPERUSER;
	}
}
