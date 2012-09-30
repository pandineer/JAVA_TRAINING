/*
 * 練習問題22.7 p.570
 * 期待されるデータのセル数を引数として渡せるようにreadCSVTableを書き直しなさい。
 */

package ch22.ex22_07;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class EX2207
{
    // static final int CELLS = 4;

    public static List<String[]> readCSVTable(Readable source, int cellsNumber)
            throws IOException
    {
        Scanner in = new Scanner(source);
        List<String[]> vals = new ArrayList<String[]>();
        // String exp = "^(.*),(.*),(.*),(.*)";
        StringBuffer exp = new StringBuffer("^");
        for (int i = 0; i < cellsNumber; i++)
        {
            exp.append("(.*)");
            if (cellsNumber != i + 1)
            {
                exp.append(",");
            }
        }
        Pattern pat = Pattern.compile(exp.toString(), Pattern.MULTILINE);
        while (in.hasNextLine())
        {
            String line = in.findInLine(pat);
            if (line != null)
            {
                // String[] cells = new String[CELLS];
                String[] cells = new String[cellsNumber];
                MatchResult match = in.match();
                // for (int i = 0; i < CELLS; i++)
                for (int i = 0; i < cellsNumber; i++)
                {
                    cells[i] = match.group(i + 1);
                }
                vals.add(cells);
                in.nextLine(); // 改行を読み飛ばし
            }
            else
            {
                throw new IOException("input format error");
            }
        }

        IOException ex = in.ioException();
        if (ex != null)
        {
            throw ex;
        }

        return vals;
    }

    public static void main(String[] args)
    {
        // StringReader test = new StringReader("1, 2, 3, 4\n5, 6, 7, 8\n");
        StringReader test = new StringReader("1, 2, 3\n4, 5, 6\n7, 8, 9\n");
        List<String[]> result = null;
        try
        {
            // result = EX2207.readCSVTable(test);
            result = EX2207.readCSVTable(test,  3);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        for (int i = 0; i< result.size(); i++)
        {
            System.out.print(i + ": ");
            for (int j = 0; j < result.get(i).length; j++)
            {
                System.out.print(result.get(i)[j] + " ");
            }
            System.out.println("");
        }
    }
}
