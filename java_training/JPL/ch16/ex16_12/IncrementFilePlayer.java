package ch16.ex16_12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ch16.ex16_12.Game.Reply;
import ch16.ex16_12.PlayerLoader;

public class IncrementFilePlayer extends Player
{
    private int incrementValue = 1;
    public IncrementFilePlayer()
    {
        super();
        java.net.URL f = PlayerLoader.findResouce("C:\\home\\miyahara\\90_warehouse\\01_git\\JAVA_TRAINING\\java_training\\JPL\\ch16\\ex16_12\\test.txt");

        try
        {
            System.out.println(f.getFile());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        try
        {
            FileReader in = new FileReader(f.getFile());
            BufferedReader br = new BufferedReader(in);
            incrementValue = Integer.valueOf(br.readLine());
            System.out.println("IncrementFilePlayer uses: " + incrementValue);
            br.close();
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }

    @Override
    public int strategy(int previous, Reply result)
    {
        switch (result)
        {
            case EQUAL:
                return previous;
            case GREATER:
                return previous - 1;
            case LESS:
                return previous + incrementValue;
            default:
                return previous;
        }
    }

}
