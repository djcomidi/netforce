import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

class Level205
{

    private byte pwdBytes[] = { 0 };
    private final String USER = "Administrator";
    private final char ALFABET[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Level205() {
        try {
            URL url = new URL("http://www.net-force.nl/challenge/level205/password.obj");
            HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
            ObjectInputStream objectinputstream = new ObjectInputStream(httpurlconnection.getInputStream());
            AccessDescriptor accessdescriptor = (AccessDescriptor)objectinputstream.readObject();
            objectinputstream.close();
            pwdBytes = accessdescriptor.getPassword(USER);
            displayBytes(pwdBytes);
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    private void displayBytes(byte byteArray[]) {
        int blen = byteArray.length;
        System.out.print(blen+" bytes: ");
        for (byte b: byteArray) {
            System.out.printf("%d ", b);
        }
        System.out.println();
    }

    private byte[] getEncrypted(String s) {
        byte outBytes[] = { 0 };
        try {
            byte inBytes[] = s.getBytes("UTF-8");
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(inBytes);
            outBytes = messagedigest.digest();
        } catch(Exception exception) {
            System.out.println("error while encrypting password" + exception.toString());
            outBytes = null;
        }
        return outBytes;
    }

    private boolean byteArrayCompare(byte arrA[], byte arrB[]) {
        if (arrA.length != arrB.length) {
            return false;
        }
        boolean allEqual = true;
        for(int i = 0; i < arrA.length; i++) {
            allEqual &= arrA[i] == arrB[i];
        }
        return allEqual;
    }

    public boolean findSolution(int length) {
        return findSolution("", length);
    }

    private boolean findSolution(String s, int length) {
        boolean foundSolution = false;
        if (length == 0) {
            if (byteArrayCompare(getEncrypted(s),pwdBytes)) {
                System.out.println("password is " + s);
                try {
                    displayBytes(getEncrypted(s));
                } catch( Exception e ) { }
                foundSolution = true;
            }
        } else {
            for (char c : ALFABET) {
                foundSolution |= findSolution(s + c, length - 1);
            }
        }
        return foundSolution;
    }

    public static void main(String args[]) {
        Level205 lvl = new Level205();
        boolean foundSol = false;
        for (int len = 1; len < 10 && !foundSol; len++) {
            foundSol |= lvl.findSolution(len);
        }
    }
}
