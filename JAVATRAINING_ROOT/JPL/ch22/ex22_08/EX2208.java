/*
 * 練習問題22.8 p.570
 * 現状では、readCSVTableは、それが期待する入力形式に関して、厳格すぎると同時に寛容すぎます。
 * 入力の終わりに空行があるとIOExceptionをスローしますので、厳格すぎます。
 * 4つ以上のカンマを持つ行でも例外が発生しませんので、寛容すぎます。
 * この両方の問題を修正しなさい。
 */

/*
 * 練習問題22.7 p.570
 * 期待されるデータのセル数を引数として渡せるようにreadCSVTableを書き直しなさい。
 */

package ch22.ex22_08;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class EX2208
{
    // static final int CELLS = 4;

    public static List<String[]> readCSVTable(Readable source, int cellsNumber)
            throws IOException
    {
        Scanner in = new Scanner(source);
        List<String[]> vals = new ArrayList<String[]>();
        // String exp = "^(.*),(.*),(.*),(.*)";
        StringBuffer exp = new StringBuffer("^");

        Boolean nullLine = false;  // 空行だったらtrue, 空行の後にも行が続くなら例外をスローする
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
            if (true == nullLine)
            {
                throw new IOException("input format error");
            }
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
                    if (cells[i].indexOf(",") != -1)
                    {
                        throw new IOException("Comma number maybe incorrect. ");
                    }
                }
                vals.add(cells);
                in.nextLine(); // 改行を読み飛ばし
            }
            else
            {
                nullLine = true;
                in.nextLine(); // 改行を読み飛ばし
                // throw new IOException("input format error");
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
        // StringReader test = new StringReader("1, 2, 3\n4, 5, 6\n\n7, 8, 9\n\n"); // input format error case
        // StringReader test = new StringReader("1, 2, a, 3\n4, 5, 6\n7, 8, 9\n\n"); // comma number incorrect case
        StringReader test = new StringReader("1, 2, 3\n4, 5, 6\n7, 8, 9\n\n"); // normal case
        List<String[]> result = null;
        try
        {
            // result = EX2208.readCSVTable(test);
            result = EX2208.readCSVTable(test,  3);
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
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
