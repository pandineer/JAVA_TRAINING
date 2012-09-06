package ch20.ex20_05;

import java.io.*;

public class FindWord
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("need word and file");
        }

        String match = args[0];
        FileReader fileIn = new FileReader(args[1]);
        LineNumberReader in = new LineNumberReader(fileIn);
        int ch;
        int count = 0;
        while((ch = in.read()) != -1)
        {
            if (ch == match.charAt(count))
            {
                count++;
            }
            else
            {
                count = 0;
            }
            if (match.length() == count)
            {
                System.out.println("Find!! at: " + (in.getLineNumber() + 1));
                count = 0;
            }
        }
    }

}
