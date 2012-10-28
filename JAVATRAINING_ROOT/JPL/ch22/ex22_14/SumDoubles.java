/*
 * 練習問題22.14 p.575
 * 複数の浮動小数点数字を含む文字列を取り、区切り文字として空白を使用して分解して、
 * 数字の合計を返すメソッドを書きなさい。
 */

package ch22.ex22_14;

import java.util.StringTokenizer;

public class SumDoubles
{
    public static double sumDoubles(String source)
    {
        double result = 0.0;
        StringTokenizer strtok = new StringTokenizer(source);

        while(strtok.hasMoreTokens())
        {
            result += Double.valueOf(strtok.nextToken());
        }

        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(SumDoubles.sumDoubles("1.1 2.2 3.3"));
        System.out.println(SumDoubles.sumDoubles("1.0 2.5 100.3"));
    }

}
