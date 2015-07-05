public class Reflector
{
    public static final int REFLECTSIZE = 26;
    int[] reflect = new int[26];

    public static final int[] I = { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };

    public Reflector(int reflectorIndex)
    {
        setReflector(reflectorIndex);
    }

    private void setReflector(int id)
    {
        switch (id) {
        case 1:
            this.reflect = I;
        }
    }

    public int send(int signal)
    {
        return this.reflect[signal];
    }
}
