package solver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LoginChallengeSolver {
	private MessageDigest msgDigest;
	private String[] passwords = null;
	private int found = 0;

	public LoginChallengeSolver() {
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			msgDigest = null;
		}
	}

	private void checkPasswordWithUsers(String password) {
		byte[] bytesB = msgDigest.digest(password.getBytes());
		for (int id = 0; id < Data.USERCOUNT; id++) {
			if (passwords[id] == null && Arrays.equals(Data.PASSWORDBYTES[id], bytesB)) {
				this.log("User '" + Data.USERNAMES[id] + "' has password '" + password + "'");
				passwords[id] = password;
				found++;
			}
		}
	}

	public void findAllPasswords() {
		passwords = new String[] { null, null, null, null, null, null };
		found = 0;
		PasswordGenerator passgen = null;
		for (int length = 1; length < 7 && found < Data.USERCOUNT; length++) {
			passgen = new PasswordGenerator(length, "aA1!");
			while (passgen.hasNext() && found < Data.USERCOUNT) {
				String pwd = passgen.next();
				checkPasswordWithUsers(pwd);
			}
		}
	}

	public String getPassword(int userid) {
		return passwords[userid];
	}

	public void log(String message) {
		SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd HH:mm:ss.SSS");
		String d = sdf.format(new Date());
		System.out.println("[" + d + "] " + message);
	}

    public static void printJohnData() {
        for (int id = 0; id < Data.USERCOUNT; id++) {
            StringBuilder sb = new StringBuilder();
            sb.append(Data.USERNAMES[id]);
            sb.append(":");
            for (byte b : Data.PASSWORDBYTES[id]) {
                sb.append(String.format("%02x", (b & 0xFF)));
            }
            System.out.println(sb.toString());
        }
    }

	public static void main(String[] args) {
	    if (args.length == 1 && "--john".equals(args[0])) {
	        LoginChallengeSolver.printJohnData();
        } else {
            LoginChallengeSolver solver = new LoginChallengeSolver();
		    solver.findAllPasswords();
		}
	}

}
