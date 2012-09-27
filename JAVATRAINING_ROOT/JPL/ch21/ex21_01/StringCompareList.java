package ch21.ex21_01;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class StringCompareList
{
    ArrayList<String> list = new ArrayList<String>();
    public void createStringList()
    {
        FileReader fileIn = null;
        try
        {
            fileIn = new FileReader("JPL/ch21/ex21_01/test.txt");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        LineNumberReader in = new LineNumberReader(fileIn);
        int ch;
        StringBuffer line = new StringBuffer("");

        try
        {
            while ((ch = in.read()) != -1)
            {
                if (ch != '\n')
                {
                    line.append(String.valueOf((char)ch));
                }
                else
                {
                    addComparedStringToList(line.toString());
                    line.delete(0, line.length());
                }
            }
            if (line.length() != 0)
            {
                addComparedStringToList(line.toString());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void addComparedStringToList(String targetString)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if (targetString.compareTo((String)list.get(i)) < 0)
            {
                list.add(i, targetString);
                return;
            }
        }
        list.add(targetString);
    }

    public void showList()
    {
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }


    public static void main(String[] args)
    {
        StringCompareList test = new StringCompareList();
        test.createStringList();
        test.showList();
    }

}
