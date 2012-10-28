/*
 * 練習問題22.9 p.570
 * 286頁の正規表現の効率性の議論を参照して、CSVの行を解析するパターンを少なくとも4つ
 * 考え出しなさい。
 * （ヒント：286頁での忠告に加えて、最長一致の量指定子（greedy qyantifier）と最短一致の
 * 量指定子（non-greedy quantifier）の使用を考えてみてください。）
 * 各パターンの効率性を比較するベンチマークプログラムを書きなさい。
 * そして、カンマ間が短い文字列と非常に長い文字列の両方でテストすることを忘れないでください。
 */

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

package ch22.ex22_09;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class EX2209
{
    // static final int CELLS = 4;

    public static List<String[]> readCSVTable1(Readable source, int cellsNumber)
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




    public static List<String[]> readCSVTable2(Readable source, int cellsNumber)
            throws IOException
            {
                Scanner in = new Scanner(source);
                List<String[]> vals = new ArrayList<String[]>();
                // String exp = "^(.*),(.*),(.*),(.*)";
                StringBuffer exp = new StringBuffer("^");

                Boolean nullLine = false;  // 空行だったらtrue, 空行の後にも行が続くなら例外をスローする
                for (int i = 0; i < cellsNumber; i++)
                {
                    // exp.append("(.*)");
                    exp.append("([^,]*)");
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




    public static List<String[]> readCSVTable3(Readable source, int cellsNumber)
            throws IOException
            {
                Scanner in = new Scanner(source);
                List<String[]> vals = new ArrayList<String[]>();
                // String exp = "^(.*),(.*),(.*),(.*)";
                StringBuffer exp = new StringBuffer("^");

                Boolean nullLine = false;  // 空行だったらtrue, 空行の後にも行が続くなら例外をスローする
                for (int i = 0; i < cellsNumber; i++)
                {
                    // exp.append("(.*)");
                    exp.append("(.+)");
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





    public static List<String[]> readCSVTable4(Readable source, int cellsNumber)
            throws IOException
            {
                Scanner in = new Scanner(source);
                List<String[]> vals = new ArrayList<String[]>();
                // String exp = "^(.*),(.*),(.*),(.*)";
                StringBuffer exp = new StringBuffer("^");

                Boolean nullLine = false;  // 空行だったらtrue, 空行の後にも行が続くなら例外をスローする
                for (int i = 0; i < cellsNumber; i++)
                {
                    // exp.append("(.*)");
                    exp.append("(.+?)");
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


    public static void test(Reader target, int cellNumber, String comment, int method)
    {
        // List<String[]> result = null;
        long start = 0;
        long stop = 0;

        // result = null;
        start = 0;
        stop = 0;

        System.out.println(comment);
        start = System.currentTimeMillis();
        try
        {
            switch (method)
            {
                case 1:
                    // result = EX2209.readCSVTable1(target,  cellNumber);
                    EX2209.readCSVTable1(target,  cellNumber);
                    break;
                case 2:
                    // result = EX2209.readCSVTable2(target,  cellNumber);
                    EX2209.readCSVTable2(target,  cellNumber);
                    break;
                case 3:
                    // result = EX2209.readCSVTable3(target,  cellNumber);
                    EX2209.readCSVTable3(target,  cellNumber);
                    break;
                case 4:
                    // result = EX2209.readCSVTable4(target,  cellNumber);
                    EX2209.readCSVTable4(target,  cellNumber);
                    break;
            }

            /*
            for (int i = 0; i< result.size(); i++)
            {
                System.out.print(i + ": ");
                for (int j = 0; j < result.get(i).length; j++)
                {
                    System.out.print(result.get(i)[j] + " ");
                }
                System.out.println("");
            }
            */
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start));
        // System.out.println(start);
        // System.out.println(stop);
    }

    public static void main(String[] args)
    {
        try
        {
            FileReader testShort1 = new FileReader("JPL/ch22/ex22_09/testShort.csv");
            FileReader testLong1 = new FileReader("JPL/ch22/ex22_09/testLong.csv");

            EX2209.test(testShort1, 3, "Short, method1", 1);
            EX2209.test(testLong1, 3, "Long, method1", 1);

            FileReader testShort2 = new FileReader("JPL/ch22/ex22_09/testShort.csv");
            FileReader testLong2 = new FileReader("JPL/ch22/ex22_09/testLong.csv");

            EX2209.test(testShort2, 3, "Short, method2", 2);
            EX2209.test(testLong2, 3, "Long, method2", 2);

            FileReader testShort3 = new FileReader("JPL/ch22/ex22_09/testShort.csv");
            FileReader testLong3 = new FileReader("JPL/ch22/ex22_09/testLong.csv");

            EX2209.test(testShort3, 3, "Short, method3", 3);
            EX2209.test(testLong3, 3, "Long, method3", 3);

            FileReader testShort4 = new FileReader("JPL/ch22/ex22_09/testShort.csv");
            FileReader testLong4 = new FileReader("JPL/ch22/ex22_09/testLong.csv");

            EX2209.test(testShort4, 3, "Short, method4", 4);
            EX2209.test(testLong4, 3, "Long, method4", 4);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
