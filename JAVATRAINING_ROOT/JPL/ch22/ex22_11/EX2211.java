/*
 * 練習問題22.11 p.573
 * Scannerではなく、StreamTokenizerを使用したreadCSVTableを書きなさい
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

package ch22.ex22_11;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class EX2211
{
    public static List<String[]> readCSVTable(Reader source, int cellsNumber)
            throws IOException
    {
        StreamTokenizer st = new StreamTokenizer(source);
        st.quoteChar(',');
        st.eolIsSignificant(true);
        List<String[]> vals = new ArrayList<String[]>();
        int count = 0;

        st.nextToken();

        while(st.ttype != StreamTokenizer.TT_EOF)
        {
            String[] cells = new String[cellsNumber];
            count = 0;
            while(st.ttype != StreamTokenizer.TT_EOL)
            {
                if (count >= cellsNumber)
                {
                    throw new IOException("input format error, count = " + count);
                }
                if (st.ttype == StreamTokenizer.TT_WORD || st.ttype == ',')
                {
                    cells[count++] = st.sval;
                }
                else if (st.ttype == StreamTokenizer.TT_NUMBER)
                {
                    if ((st.nval - Math.floor(st.nval)) == 0)
                    {
                        cells[count++] = String.valueOf((int)st.nval);
                    }
                    else
                    {
                        cells[count++] = String.valueOf(st.nval);
                    }

                }
                st.nextToken();
            }
            if (count > 0)
            {
                if (count == cellsNumber)
                {
                    vals.add(cells);
                }
                else
                {
                    throw new IOException("input format error, count = " + count);
                }
            }
            st.nextToken();
        }
        return vals;
    }

    public static void main(String[] args)
    {
        // StringReader test = new StringReader("a,b,c\nd,e,f\n\ng,h,i\n\n"); // input format error case
        // StringReader test = new StringReader("a,b,c,d\ne,f,g\nh,i,j\n\n"); // comma number incorrect case
        // StringReader test = new StringReader("a,b\ne,f,g\nh,i,j\n\n"); // comma number incorrect case
        StringReader test = new StringReader("a,b,c\nd,e,f\n7,8,9\n\n"); // normal case

        List<String[]> result = null;
        try
        {
            result = EX2211.readCSVTable(test,  3);
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
