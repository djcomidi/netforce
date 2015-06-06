import java.io.Serializable;

class AccessDescriptor
    implements Serializable
{

    public AccessDescriptor()
    {
    }

    public boolean checkUserName(String s)
    {
        boolean flag = false;
        for(int i = 0; i < users.length; i++)
            if(s.equals(users[i]))
                flag = true;

        return flag;
    }

    public byte[] getPassword(String s)
    {
        byte abyte0[] = {
            0
        };
        for(int i = 0; i < users.length; i++)
            if(s.equals(users[i]))
                abyte0 = passes[i];

        return abyte0;
    }

    public void setPassword(String s, byte abyte0[])
    {
        for(int i = 0; i < users.length; i++)
            if(s.equals(users[i]))
                passes[i] = abyte0;

    }

    private String users[] = {
        "Administrator"
    };
    private byte passes[][] = {
        {
            -1
        }
    };
}