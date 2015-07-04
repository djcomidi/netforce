package solver;

public interface Data {
	public final static String[] USERNAMES = { "guest", "bin", "system", "NetForce", "Admin", "DarkLord" };

	public final static String[] USERTYPES = { "user", "user", "superuser", "admin", "admin", "admin" };

	public final static byte[][] PASSWORDBYTES = new byte[][] {
			{ 8, 78, 3, 67, -96, 72, 111, -16, 85, 48, -33, 108, 112, 92, -117, -76 },
			{ -63, 17, 27, -43, 18, -78, -98, -126, 27, 18, 11, -122, 68, 96, 38, -72 },
			{ 21, 31, 34, 86, 115, 84, 97, -80, 11, 108, 13, 76, 11, -122, 82, -28 },
			{ -39, -14, 16, -15, 24, -82, -1, -23, 53, -86, -47, -63, 25, -112, -56, -77 },
			{ -23, -48, 25, 64, 79, 9, -108, 72, -78, 19, 84, 86, 101, -38, -119, -76 },
			{ 73, -27, -24, -100, -123, -29, 77, 18, 63, 113, 106, 79, -13, -75, -3, -71 } };

	public final static int USERCOUNT = 6;
}
